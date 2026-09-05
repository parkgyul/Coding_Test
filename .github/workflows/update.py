#!/usr/bin/env python3
"""
알고리즘 풀이 저장소의 README.md를 자동 생성한다.

- 푼 날짜는 git 히스토리에서 "그 문제 폴더가 처음 커밋된 시점"을 가져온다.
- 폴더를 git mv 로 옮겨도 폴더 이름만 같으면 최초 날짜가 그대로 유지된다.
- 플랫폼/난이도 폴더 깊이가 달라도 알아서 문제 폴더를 찾아낸다.
"""

from __future__ import annotations

import re
import subprocess
from collections import defaultdict
from datetime import date
from pathlib import Path
from urllib.parse import quote

README_FILE = "README.md"

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


# --------------------------------------------------------------------------
# 플랫폼 설정: 여기만 고치면 플랫폼을 추가/변경할 수 있다
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
    # 0001-two-sum → two-sum
    m = re.match(r"\d+[-._ ]+(.+)", problem)
    if not m:
        return None
    slug = re.sub(r"[^a-z0-9]+", "-", m.group(1).lower()).strip("-")
    return f"https://leetcode.com/problems/{slug}/"


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
        "dirs": ["LeetCode", "leetcode"],
        "title": "LeetCode",
        "emoji": "⚡",
        "tier_order": ["Easy", "Medium", "Hard"],
        "url": leetcode_url,
    },
]


# --------------------------------------------------------------------------
# git
# --------------------------------------------------------------------------

def repo_root() -> Path:
    try:
        out = subprocess.run(
            ["git", "rev-parse", "--show-toplevel"],
            capture_output=True, text=True, check=True,
        )
        return Path(out.stdout.strip())
    except Exception:
        return Path.cwd()


def collect_commit_dates(root: Path) -> dict[str, str]:
    """폴더 이름 → 그 폴더의 파일이 처음 커밋된 날짜.

    전체 히스토리를 한 번만 훑어서 dict 를 만든다. 파일마다 git 을 부르는 것보다
    훨씬 빠르고, 폴더 이름으로 묶기 때문에 경로가 바뀌어도 최초 날짜가 살아남는다.
    """
    marker = "__COMMIT__"
    cmd = [
        "git", "-c", "core.quotePath=false", "-C", str(root), "log",
        "--reverse", "--no-renames", "--date=short",
        f"--pretty=format:{marker}%ad", "--name-only",
    ]
    try:
        out = subprocess.run(cmd, capture_output=True, text=True,
                             encoding="utf-8", check=True).stdout
    except Exception as e:
        print(f"[warn] git log 실패, 날짜를 오늘로 채웁니다: {e}")
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
            # --reverse 라서 먼저 등장한 쪽이 가장 오래된 커밋이다
            dates.setdefault(parent, current)
    return dates


# --------------------------------------------------------------------------
# 스캔
# --------------------------------------------------------------------------

def find_platform(name: str) -> dict | None:
    for p in PLATFORMS:
        if name in p["dirs"]:
            return p
    return None


def solution_files(directory: Path) -> list[Path]:
    files = [
        f for f in sorted(directory.iterdir())
        if f.is_file()
        and f.name not in SKIP_FILES
        and f.suffix in LANGUAGES
    ]
    return files


def scan_platform(platform_dir: Path) -> list[dict]:
    """플랫폼 폴더 아래에서 '코드 파일을 직접 담고 있는 폴더'를 문제로 본다."""
    problems = []
    for directory in sorted(platform_dir.rglob("*")):
        if not directory.is_dir():
            continue
        if any(part in SKIP_DIRS for part in directory.parts):
            continue
        files = solution_files(directory)
        if not files:
            continue
        rel = directory.relative_to(platform_dir).parts
        tier = rel[-2] if len(rel) >= 2 else "미분류"
        problems.append({
            "problem": directory.name,
            "tier": tier,
            "path": directory,
            "files": files,
            "languages": sorted({LANGUAGES[f.suffix] for f in files}),
        })
    return problems


def natural_key(text: str):
    return [int(t) if t.isdigit() else t.lower()
            for t in re.split(r"(\d+)", text)]


def tier_key(tier: str, order: list[str]):
    low = tier.lower().replace(" ", "").replace(".", "")
    for i, known in enumerate(order):
        if low.startswith(known.lower()):
            return (i, natural_key(tier))
    return (len(order), natural_key(tier))


# --------------------------------------------------------------------------
# 렌더링
# --------------------------------------------------------------------------

def link(path: Path, root: Path) -> str:
    return quote(str(path.relative_to(root)).replace("\\", "/"))


def render(root: Path, data: list[tuple[dict, list[dict]]], dates: dict) -> str:
    today = date.today().strftime("%Y.%m.%d")
    total = sum(len(p) for _, p in data)

    lines = [
        "# 🗂 Algorithm Solutions",
        "",
        "백준 · 프로그래머스 · LeetCode 풀이를 자동으로 정리한 저장소입니다.",
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
        if not problems:
            continue
        lines += ["---", "",
                  f"## {platform['emoji']} {platform['title']}", ""]

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
                url = platform["url"](p["problem"])
                title = f"[{p['problem']}]({url})" if url else p["problem"]
                langs = ", ".join(p["languages"])
                code = " · ".join(
                    f"[{f.suffix.lstrip('.')}]({link(f, root)})" for f in p["files"]
                )
                when = dates.get(p["problem"], today).replace("-", ".")
                lines.append(f"| {title} | {langs} | {code} | {when} |")
            lines += ["", "</details>", ""]

    return "\n".join(lines).rstrip() + "\n"


# --------------------------------------------------------------------------

def main() -> None:
    root = repo_root()
    dates = collect_commit_dates(root)

    data = []
    for child in sorted(root.iterdir()):
        if not child.is_dir() or child.name in SKIP_DIRS:
            continue
        platform = find_platform(child.name)
        if platform is None:
            continue
        problems = scan_platform(child)
        if problems:
            data.append((platform, problems))
            print(f"{platform['title']}: {len(problems)}문제")

    if not data:
        print("[warn] 플랫폼 폴더를 못 찾았습니다. PLATFORMS 설정을 확인하세요.")

    (root / README_FILE).write_text(render(root, data, dates), encoding="utf-8")
    print(f"완료 → {root / README_FILE}")


if __name__ == "__main__":
    main()
