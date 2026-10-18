
def dfs(depth, total, numbers, target):
    if(depth == len(numbers)):
        if(target == total):
            return 1
        return 0
    
    plus = dfs(
        depth+1,
        total + numbers[depth], 
        numbers, 
        target
    )
    
    minus = dfs(
        depth+1,
        total - numbers[depth],
        numbers,
        target
    )
    
    return plus + minus
    
def solution(numbers, target):
    return dfs(0, 0, numbers, target)



