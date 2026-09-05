#!/usr/bin/env python3
"""
알고리즘 풀이 저장소의 README.md를 자동 생성한다.

푼 날짜 결정 순서
  1. .solved_dates.json 에 이미 기록된 값 (한 번 정해지면 다시 안 바뀜)
  2. git 히스토리에서 그 문제 폴더가 처음 추가된 커밋 날짜
  3. 둘 다 없으면 오늘 (새로 푼 문제)

주의: git 히스토리가 shallow clone 이면 2번이 전부 같은 날짜가 된다.
      GitHub Actions 라면 actions/checkout 에 fetch-depth: 0 을 꼭 넣을 것.
"""

from __future__ import annotations

import json
import re
import subprocess
from collections import defaultdict
from datetime import date
from pathlib import Path
from urllib.parse import quote

README_FILE = "README.md"
DATE_CACHE_FILE = ".solved_dates.json"
LINKS_FILE = "links.json"   # 자동으로 못 만드는 링크를 직접 채워두는 파일

SKIP_DIRS = {
    ".git", ".github", ".idea", ".vscode", "images", "img",
    "node_modules", "__pycache__", ".venv",
}
SKIP_FILES = {"README.md", "readme.md", "NOTES.md", ".DS_Store", ".gitignore"}

LANGUAGES = {
    ".java": "Java", ".py": "Python", ".cpp": "C++", ".cc": "C++",
    ".c": "C", ".cs": "C#", ".js": "JavaScript", ".ts": "TypeScript",
    ".kt": "Kotlin", ".go": "Go", ".rs": "Rust", ".rb": "Ruby",
    ".swift": "Swift", ".sql": "SQL", ".sh": "Shell",
}

# 문제 폴더 README 에서 원본 링크를 주워올 때 신뢰할 도메인
KNOWN_HOSTS = (
    "acmicpc.net", "programmers.co.kr", "swexpertacademy.com",
    "leetcode.com", "codetree.ai",
)


# --------------------------------------------------------------------------
# 플랫폼 설정
# --------------------------------------------------------------------------

def boj_url(problem: str) -> str | None:
    m = re.match(r"(\d+)", problem)
    return f"https://www.acmicpc.net/problem/{m.group(1)}" if m else None


def programmers_url(problem: str) -> str | None:
    m = re.match(r"(\d+)", problem)
    if not m:
        return None
    return f"https://school.programmers.co.kr/learn/courses/30/lessons/{m.group(1)}"


def leetcode_url(problem: str) -> str | None:
    m = re.match(r"\d+[-._ ]+(.+)", problem)
    if not m:
        return None
    slug = re.sub(r"[^a-z0-9]+", "-", m.group(1).lower()).strip("-")
    return f"https://leetcode.com/problems/{slug}/"


def swea_url(problem: str) -> str | None:
    # SWEA 는 문제 번호가 아니라 contestProbId 라는 내부 ID 로 접근해서
    # 번호만으로 URL 을 만들 수 없다. 폴더 안 README 의 링크를 대신 쓴다.
    return None


def codetree_url(problem: str) -> str | None:
    # 코드트리도 영문 slug 기반이라 한글 폴더명에서 URL 을 만들 수 없다.
    # links.json 이나 폴더 안 README 링크로 채운다.
    return None


PLATFORMS = [
    {
        "dirs": ["백준", "baekjoon", "BOJ"],
        "title": "백준",
        "emoji": "🐢",
        "tier_order": ["Bronze", "Silver", "Gold", "Platinum",
                       "Diamond", "Ruby", "Unrated"],
        "url": boj_url,
    },
    {
        "dirs": ["프로그래머스", "programmers"],
        "title": "프로그래머스",
        "emoji": "🧩",
        "tier_order": ["level1", "level2", "level3", "level4", "level5"],
        "url": programmers_url,
    },
    {
        "dirs": ["SWEA", "swea", "SW Expert Academy"],
        "title": "SW Expert Academy",
        "emoji": "🎯",
        "tier_order": ["D1", "D2", "D3", "D4", "D5", "D6"],
        "url": swea_url,
    },
    {
        "dirs": ["LeetCode", "leetcode"],
        "title": "LeetCode",
        "emoji": "⚡",
        "tier_order": ["Easy", "Medium", "Hard"],
        "url": leetcode_url,
    },
    {
        "dirs": ["Code_Tree", "CodeTree", "codetree", "코드트리"],
        "title": "코드트리",
        "emoji": "🌳",
        "tier_order": ["Trail", "쉬움", "보통", "어려움",
                       "Novice", "Intermediate", "Advanced", "Expert"],
        "url": codetree_url,
    },
]


# --------------------------------------------------------------------------
# git
# --------------------------------------------------------------------------

def git(root: Path, *args: str) -> str:
    out = subprocess.run(
        ["git", "-c", "core.quotePath=false", "-C", str(root), *args],
        capture_output=True, text=True, encoding="utf-8", check=True,
    )
    return out.stdout


def repo_root() -> Path:
    try:
        return Path(git(Path.cwd(), "rev-parse", "--show-toplevel").strip())
    except Exception:
        return Path.cwd()


def warn_if_shallow(root: Path) -> None:
    try:
        shallow = git(root, "rev-parse", "--is-shallow-repository").strip()
        count = int(git(root, "rev-list", "--count", "HEAD").strip())
    except Exception:
        return
    if shallow == "true":
        print("[warn] shallow clone 입니다. 모든 문제가 같은 날짜로 기록됩니다.")
        print("       로컬이면   git fetch --unshallow")
        print("       Actions 면 actions/checkout 에 fetch-depth: 0 추가")
    elif count <= 2:
        print(f"[warn] 커밋이 {count}개뿐이라 날짜를 구분할 수 없습니다.")


def collect_commit_dates(root: Path) -> dict[str, str]:
    """폴더 이름 → 그 폴더의 파일이 처음 '추가'된 커밋 날짜.

    전체 히스토리를 한 번만 훑는다. 경로가 아니라 폴더 이름을 키로 쓰기 때문에
    git mv 로 폴더를 옮겨도 최초 날짜가 유지된다.
    """
    marker = "__C__"
    try:
        out = git(
            root, "log", "--reverse", "--no-renames", "--diff-filter=A",
            "--date=short", f"--pretty=format:{marker}%ad", "--name-only",
        )
    except Exception as e:
        print(f"[warn] git log 실패: {e}")
        return {}

    dates: dict[str, str] = {}
    current = None
    for line in out.splitlines():
        line = line.strip()
        if not line:
            continue
        if line.startswith(marker):
            current = line[len(marker):]
            continue
        if current is None:
            continue
        parent = Path(line).parent.name
        if parent:
            dates.setdefault(parent, current)  # --reverse 라 첫 등장이 가장 오래됨
    return dates


# --------------------------------------------------------------------------
# 스캔
# --------------------------------------------------------------------------

def find_platform(name: str) -> dict | None:
    return next((p for p in PLATFORMS if name in p["dirs"]), None)


def solution_files(directory: Path) -> list[Path]:
    return [
        f for f in sorted(directory.iterdir())
        if f.is_file() and f.name not in SKIP_FILES and f.suffix in LANGUAGES
    ]


def url_from_readme(directory: Path) -> str | None:
    """BaekjoonHub 이 문제 폴더에 만들어둔 README 에서 원본 문제 링크를 뽑는다."""
    for name in ("README.md", "readme.md"):
        f = directory / name
        if not f.exists():
            continue
        try:
            text = f.read_text(encoding="utf-8", errors="ignore")
        except OSError:
            continue
        for url in re.findall(r"https?://[^\s)\]\"'>]+", text):
            if any(host in url for host in KNOWN_HOSTS):
                return url.rstrip(".,")
    return None


def scan_platform(platform_dir: Path) -> list[dict]:
    """코드 파일을 직접 담고 있는 폴더 하나를 문제 하나로 본다."""
    problems = []
    for directory in sorted(platform_dir.rglob("*")):
        if not directory.is_dir() or any(p in SKIP_DIRS for p in directory.parts):
            continue
        files = solution_files(directory)
        if not files:
            continue
        rel = directory.relative_to(platform_dir).parts
        problems.append({
            "problem": directory.name,
            # 플랫폼 바로 아래 첫 폴더를 난이도/분류로 본다.
            # (Trail4/챕터/문제/코드 처럼 중간 폴더가 껴 있어도 Trail4 로 묶임)
            "tier": rel[0] if len(rel) >= 2 else "미분류",
            "path": directory,
            "files": files,
            "url": url_from_readme(directory),
            "languages": sorted({LANGUAGES[f.suffix] for f in files}),
        })
    return problems


def natural_key(text: str):
    return [int(t) if t.isdigit() else t.lower() for t in re.split(r"(\d+)", text)]


def tier_key(tier: str, order: list[str]):
    low = tier.lower().replace(" ", "").replace(".", "")
    for i, known in enumerate(order):
        if low.startswith(known.lower()):
            return (i, natural_key(tier))
    return (len(order), natural_key(tier))


# --------------------------------------------------------------------------
# 렌더링
# --------------------------------------------------------------------------

def rel_link(path: Path, root: Path) -> str:
    return quote(str(path.relative_to(root)).replace("\\", "/"))


def render(root: Path, data: list[tuple[dict, list[dict]]]) -> str:
    today = date.today().strftime("%Y.%m.%d")
    total = sum(len(p) for _, p in data)

    lines = [
        "# 🗂 Algorithm Solutions",
        "",
        "백준 · 프로그래머스 · SWEA · LeetCode 풀이 기록입니다.",
        "",
        f"**총 {total}문제** · 마지막 업데이트 {today}",
        "",
        "| 플랫폼 | 문제 수 |",
        "| :--- | ---: |",
    ]
    for platform, problems in data:
        lines.append(f"| {platform['emoji']} {platform['title']} | {len(problems)} |")
    lines.append("")

    for platform, problems in data:
        lines += ["---", "", f"## {platform['emoji']} {platform['title']}", ""]

        by_tier = defaultdict(list)
        for p in problems:
            by_tier[p["tier"]].append(p)

        for tier in sorted(by_tier, key=lambda t: tier_key(t, platform["tier_order"])):
            rows = sorted(by_tier[tier], key=lambda p: natural_key(p["problem"]))
            lines += [
                "<details>",
                f"<summary><b>{tier}</b> — {len(rows)}문제</summary>",
                "",
                "| 문제 | 언어 | 풀이 | 푼 날짜 |",
                "| :--- | :---: | :---: | :---: |",
            ]
            for p in rows:
                title = f"[{p['problem']}]({p['url']})" if p["url"] else p["problem"]
                langs = ", ".join(p["languages"])
                code = " · ".join(
                    f"[{f.suffix.lstrip('.')}]({rel_link(f, root)})" for f in p["files"]
                )
                lines.append(f"| {title} | {langs} | {code} | {p['solved']} |")
            lines += ["", "</details>", ""]

    return "\n".join(lines).rstrip() + "\n"


# --------------------------------------------------------------------------

def main() -> None:
    root = repo_root()
    warn_if_shallow(root)

    cache_path = root / DATE_CACHE_FILE
    try:
        cache = json.loads(cache_path.read_text(encoding="utf-8"))
    except (OSError, ValueError):
        cache = {}

    git_dates = collect_commit_dates(root)
    today = date.today().strftime("%Y.%m.%d")

    try:
        links = json.loads((root / LINKS_FILE).read_text(encoding="utf-8"))
    except (OSError, ValueError):
        links = {}

    missing_links = []
    data = []
    for platform in PLATFORMS:  # README 에 찍히는 순서 = 여기 정의된 순서
        child = next((root / d for d in platform["dirs"] if (root / d).is_dir()), None)
        if child is None:
            continue
        problems = scan_platform(child)
        if not problems:
            continue

        for p in problems:
            key = f"{platform['title']}/{p['problem']}"

            # 링크: links.json > 문제 폴더 README > 번호로 생성
            p["url"] = links.get(key) or p["url"] or platform["url"](p["problem"])
            if not p["url"]:
                missing_links.append(key)

            if key in cache:
                p["solved"] = cache[key]
            else:
                p["solved"] = git_dates.get(p["problem"], today).replace("-", ".")
                cache[key] = p["solved"]

        data.append((platform, problems))
        print(f"{platform['title']}: {len(problems)}문제")

    if not data:
        print("[warn] 플랫폼 폴더를 못 찾았습니다. PLATFORMS 의 dirs 를 확인하세요.")
        return

    (root / README_FILE).write_text(render(root, data), encoding="utf-8")
    cache_path.write_text(
        json.dumps(cache, ensure_ascii=False, indent=2, sort_keys=True) + "\n",
        encoding="utf-8",
    )

    if missing_links:
        # 링크를 못 만든 문제는 links.json 틀을 만들어 두고 직접 채우게 한다
        template = {**{k: "" for k in missing_links}, **links}
        (root / LINKS_FILE).write_text(
            json.dumps(template, ensure_ascii=False, indent=2, sort_keys=True) + "\n",
            encoding="utf-8",
        )
        print(f"[info] 링크 없는 문제 {len(missing_links)}개 → {LINKS_FILE} 에 채워주세요")

    print(f"완료 → {root / README_FILE}")


if __name__ == "__main__":
    main()
