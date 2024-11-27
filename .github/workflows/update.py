import os
from urllib import parse
from datetime import datetime

HEADER = """# 
# 백준 문제 풀이 목록

"""

def get_last_modified_time(filepath):
    # 파일의 마지막 수정 시간을 가져와 형식화합니다.
    timestamp = os.path.getmtime(filepath)
    return datetime.fromtimestamp(timestamp).strftime('%Y.%m.%d')

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
                content += "| 문제번호 | 링크 | 마지막 수정 |\n"
                content += "| ----- | ----- | ----- |\n"
            directories.append(directory)

        for file in files:
            if category not in solveds:
                file_path = os.path.join(root, file)
                last_modified = get_last_modified_time(file_path)
                content += "|{}|[링크]({})|{}|\n".format(category, parse.quote(file_path), last_modified)
                solveds.append(category)
                print(f"category : {category}, last_modified: {last_modified}")

    with open("README.md", "w") as fd:
        fd.write(content)
        
if __name__ == "__main__":
    main()
