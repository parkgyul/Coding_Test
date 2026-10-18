def solution(nums):
    answer = 0
    select_count = len(nums) // 2
    
    kind_count = len(set(nums))
    
    return min(select_count, kind_count)