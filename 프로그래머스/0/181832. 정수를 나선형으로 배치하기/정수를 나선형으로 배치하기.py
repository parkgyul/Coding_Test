def solution(n):
    board = [[0] * n for _ in range(n)]
    dr = [0, 1, 0, -1]
    dc = [1, 0, -1, 0]
    
    r, c = 0, 0
    dir = 0
    number = 1
    
    while number <= n*n:
        board[r][c] = number
        number += 1
        
        nr = r + dr[dir]
        nc = c + dc[dir]
        
        if (nr < 0 or nr >= n or nc < 0 or nc >= n or board[nr][nc] != 0):
            dir = (dir+1) % 4
            nr = r + dr[dir]
            nc = c + dc[dir]
            
        r, c = nr, nc    
    
    return board