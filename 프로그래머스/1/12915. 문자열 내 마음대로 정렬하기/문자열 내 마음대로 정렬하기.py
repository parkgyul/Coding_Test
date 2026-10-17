def solution(strings, n):
    answer = sorted(strings)
    return sorted(answer, key = lambda x:x[n])