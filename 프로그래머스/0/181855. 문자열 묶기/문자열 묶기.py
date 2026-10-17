from collections import Counter
def solution(strArr):
    lengths = [len(s) for s in strArr]
    cnt = Counter(lengths)
    
    return max(cnt.values())