import os
import datetime
from urllib import parse

HEADER = """# 
# 백준 문제 풀이 목록

"""

README_FILE = "README.md"

def load_existing_data():
    """기존 README.md 파일에서 문제번호와 추가된 시간을 로드"""
    existing_data = {}
    if os.path.exists(README_FILE):
        with open(README_FILE, "r", encoding="utf-8") as file:
            lines = file.readlines()
            for line in lines:
                if line.startswith("|") and len(line.split("|")) >= 4:
                    parts = line.strip().split("|")
                    problem_number = parts[1].strip()
                    added_time = parts[3].strip()
                    existing_data[problem_number] = added_time
    return existing_data

def main():
    content = HEADER
    directories = []
    solveds = []
    
    existing_data = load_existing_data()

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
                content += "| 문제번호 | 링크 | 푼 날짜 |\n"
                content += "| ----- | ----- | -------------- |\n"
            directories.append(directory)

        for file in files:
            if category not in solveds:
                file_link = parse.quote(os.path.join(root, file))
                
                # 기존 데이터에 있으면 기존 시간 유지, 없으면 현재 시간 기록
                added_time = existing_data.get(category, datetime.datetime.now().strftime("%Y.%m.%d"))

                content += "|{}|[링크]({})|{}|\n".format(category, file_link, added_time)
                solveds.append(category)
                print(f"category : {category}, added_time : {added_time}")

    with open(README_FILE, "w", encoding="utf-8") as fd:
        fd.write(content)
        
if __name__ == "__main__":
    main()
