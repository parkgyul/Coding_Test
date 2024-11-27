import os
import subprocess
from urllib import parse

HEADER = """# 
# 백준 문제 풀이 목록

"""

def get_last_commit_time(file_path):
    try:
        # Git 명령으로 마지막 커밋 날짜 가져오기
        result = subprocess.check_output(["git", "log", "-1", "--format=%ci", "--", file_path], encoding="utf-8")
        # YYYY-MM-DD 포맷으로 변환
        return result.strip().split(" ")[0].replace("-", ".")
    except subprocess.CalledProcessError:
        return "Unknown"

def main():
    content = ""
    content += HEADER
    
    directories = []
    solveds = []

    for root, dirs, files in os.walk("."):
        dirs.sort()
        if root == '.':
            for dir in ('.git', '.github'):
                try:
                    dirs.remove(dir)
                except ValueError:
                    pass
            continue

        category = os.path.basename(root)
        
        if category == 'images':
            continue
        
        directory = os.path.basename(os.path.dirname(root))
        
        if directory == '.':
            continue
            
        if directory not in directories:
            if directory in ["백준"]:
                content += "## 📚 {}\n".format(directory)
            else:
                content += "### 🚀 {}\n".format(directory)
                content += "| 문제번호 | 링크 | 마지막 커밋 시간 |\n"
                content += "| ----- | ----- | ----- |\n"
            directories.append(directory)

        for file in files:
            if file.endswith(".md"):  # 예시로 .md 파일만 처리
                file_path = os.path.join(root, file)
                last_commit_time = get_last_commit_time(file_path)

                if category not in solveds:
                    content += "|{}|[링크]({})|{}|\n".format(
                        category, 
                        parse.quote(file_path), 
                        last_commit_time
                    )
                    solveds.append(category)
                    print(f"category: {category}, last commit time: {last_commit_time}")

    with open("README.md", "w") as fd:
        fd.write(content)
        
if __name__ == "__main__":
    main()
