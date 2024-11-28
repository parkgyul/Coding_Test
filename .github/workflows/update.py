import os
import subprocess
from urllib import parse

HEADER = """# 
# 백준 문제 풀이 목록

"""

def get_latest_commit_date(file_path):
    """
    Get the latest commit date for a specific file.
    """
    try:
        result = subprocess.run(
            ["git", "log", "-1", "--format=%ci", file_path],
            stdout=subprocess.PIPE,
            stderr=subprocess.PIPE,
            text=True,
        )
        if result.returncode == 0:
            return result.stdout.strip()  # Commit date
        else:
            return "N/A"  # If no commit info is found
    except Exception as e:
        print(f"Error fetching commit date for {file_path}: {e}")
        return "N/A"

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
                content += "| 문제번호 | 링크 | 커밋 날짜 |\n"
                content += "| ----- | ----- | ----- |\n"
            directories.append(directory)

        for file in files:
            file_path = os.path.join(root, file)
            commit_date = get_latest_commit_date(file_path)  # Fetch commit date for the file
            problem_number = os.path.splitext(file)[0]  # Assume file name starts with problem number

            if problem_number not in solveds:
                content += "|{}|[링크]({})|{}|\n".format(
                    problem_number, parse.quote(file_path), commit_date
                )
                solveds.append(problem_number)
                print(f"Processed: {problem_number}, Commit Date: {commit_date}")

    with open("README.md", "w") as fd:
        fd.write(content)

if __name__ == "__main__":
    main()
