from itertools import combinations
def solution(numbers):
    
    com = list(combinations(numbers, 2))
    answer = sorted(set([a+b for a, b in com]))
    return answer