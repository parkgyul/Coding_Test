cnt = 0

def dfs(depth, sum, numbers, target):
    if(depth == len(numbers)):
        if(target == sum):
            global cnt
            cnt += 1
        return
    
    dfs(depth+1, sum+numbers[depth], numbers, target)
    dfs(depth+1, sum-numbers[depth], numbers, target)
    
def solution(numbers, target):
    
    dfs(0, 0, numbers, target)
    
    return cnt


