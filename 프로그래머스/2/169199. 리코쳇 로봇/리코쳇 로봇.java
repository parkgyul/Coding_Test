import java.util.*;

class Solution {
    static Queue<Point> q = new LinkedList<>();
    static boolean[][] visited;
    static char[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    public int solution(String[] board) {
        int answer = 0;
        
        map = new char[board.length][board[0].length()];
        visited = new boolean[board.length][board[0].length()];
        
        for(int i = 0; i < board.length; i++){
            String str = board[i];
            for(int j = 0; j < board[0].length(); j++){
                char ch = str.charAt(j);
                
                map[i][j] = ch;
                
                if(ch == 'R'){
                    q.add(new Point(i, j, 0));
                }
            }
        }
        
        
        
        return bfs();
    }
    static int bfs(){
        while(!q.isEmpty()){
            Point cur = q.poll();
            
            for(int i = 0; i < 4; i++){
                int ni = cur.i;
                int nj = cur.j;
                while( (ni >= 0 && nj >= 0 && ni < map.length && nj < map[0].length) && map[ni][nj] != 'D'){ //격자를 벗어나거나 장애물을 만남.
                    ni += dx[i];
                    nj += dy[i];
                }
                
                      ni -= dx[i];
                      nj -= dy[i];
                
                if(visited[ni][nj]) continue;
                if(map[ni][nj] == 'G') return cur.cnt+1;
                
                visited[ni][nj] = true;
                q.add(new Point(ni, nj, cur.cnt+1));
            }
        }
                      
        return -1;
    }
    
    static class Point{
        int i, j, cnt;
        
        Point(int i, int j, int cnt){
            this.i = i;
            this.j = j;
            this.cnt = cnt;
        }
    }
}