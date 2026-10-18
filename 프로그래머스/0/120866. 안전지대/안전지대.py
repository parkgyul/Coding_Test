def solution(board):
    answer = 0
    
    n = len(board)
    
    dr = [-1, -1, -1, 0, 0, 1, 1, 1]
    dc = [-1, 0, 1, -1, 1, -1, 0, 1]
    
    for r in range(n):
        for c in range(n):
            if board[r][c] == 1:
                continue
            
            for d in range(8):
                nr = r + dr[d]
                nc = c + dc[d]
                
                if(nr < 0 or nc < 0 or nr >= n or nc >= n):
                    continue
                
                if(board[nr][nc] == 1):
                    answer -= 1
                    break
            
            answer += 1
                
            
    return answer