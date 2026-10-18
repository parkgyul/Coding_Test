def solution(dirs):
    x, y = 0, 0
    
    visited = set()
    
    move = {
        "U" : (0, 1),
        "D" : (0, -1),
        "R" : (1, 0),
        "L" : (-1, 0)
    }
    
    for d in dirs:
        dx, dy = move[d]
        
        nx = x + dx
        ny = y + dy
        
        if not(-5 <= nx <= 5 and -5 <= ny <= 5):
            continue
        
        start = (x, y)
        end = (nx, ny)
        
        road = tuple(sorted([start, end]))
        
        visited.add(road)
        
        x, y = nx, ny
    
    return len(visited)