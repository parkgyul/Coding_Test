import os
import datetime
from urllib import parse

HEADER = """# 
# 백준 문제 풀이 목록

"""

README_FILE = "README.md"

def load_existing_data():
    existing_data = {}
    if os.path.exists(README_FILE):
        with open(README_FILE, "r", encoding="utf-8") as file:
            lines = file.readlines()
            for line in lines:
                if line.startswith("|") and len(line.split("|")) >= 4:
                    parts = line.strip().split("|")
                    problem_number = parts[1].strip()
                    added_time = parts[3].strip()
                    existing_data[problem_number] = added_time if added_time else None
    return existing_data

def main():
    content = HEADER
    directories = []
    problems = []  # ← 여기 모아둠
    
    existing_data = load_existing_data()

    for root, dirs, files in os.walk("."):
        dirs.sort()
        if root == '.':
            for dir in ('.git', '.github'):
                if dir in dirs:
                    dirs.remove(dir)
            continue

        category = os.path.basename(root)

        if category == 'images':
            continue
        
        directory = os.path.basename(os.path.dirname(root))
        if directory == '.':
            continue

        for file in files:
            file_link = parse.quote(os.path.join(root, file))
            added_time = existing_data.get(category, None)
            problems.append((directory, category, file_link, added_time))

    # 🔥 정렬: null 먼저, 그 다음 날짜 오름차순
    def sort_key(x):
        date = x[3]
        if date is None:
            return (0, datetime.datetime.min)
        return (1, datetime.datetime.strptime(date, "%Y.%m.%d"))

    problems.sort(key=sort_key)

    # 🔥 출력 생성
    current_directory = None

    for directory, category, file_link, added_time in problems:
        if directory != current_directory:
            if directory in ["백준"]:
                content += "## 📚 {}\n".format(directory)
            else:
                content += "### 🚀 {}\n".format(directory)
                content += "| 문제번호 | 링크 | 푼 날짜 |\n"
                content += "| ----- | ----- | -------------- |\n"
            current_directory = directory

        display_time = added_time if added_time else ""
        content += "|{}|[링크]({})|{}|\n".format(category, file_link, display_time)

    with open(README_FILE, "w", encoding="utf-8") as fd:
        fd.write(content)

if __name__ == "__main__":
    main()
