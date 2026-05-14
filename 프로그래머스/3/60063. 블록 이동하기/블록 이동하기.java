import java.util.*;
class Solution {
    static int n;
    static int[][] board;
    static boolean[][][] visited;
    public int solution(int[][] boardInput) {
        board = boardInput;
        n = boardInput.length;
        visited = new boolean[n][n][2];
        
        return bfs();
    }
    
    public int bfs(){
        Queue<Robot> q = new LinkedList<>();
        // dir = 0 : 가로 , dir = 1 : 세로
        q.add(new Robot(0,0, 0, 1, 0)); 

        visited[0][0][0] = true;
        
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        int[] rotate = {-1, 1};
        
        while(!q.isEmpty()){
            Robot cur = q.poll();
            int r1 = cur.r1;
            int c1 = cur.c1;
            int r2 = cur.r2;
            int c2 = cur.c2;
            int dir = cur.getDir();
            
            if((r1 == n-1 && c1 == n-1) || (r2 == n-1 && c2 == n-1))
                return cur.cnt;
            
            for(int i = 0; i < 4; i++){
                int nr1 = r1 + dx[i];
                int nc1 = c1 + dy[i];
                int nr2 = r2 + dx[i];
                int nc2 = c2 + dy[i];
                
                
                addNext(q, nr1, nc1, nr2, nc2, cur.cnt+1);
            }
            
            if(r1 == r2){ // 가로
                for(int d : rotate){
                    int nr = r1 + d;
                    
                    if(isEmpty(nr, c1) && isEmpty(nr, c2)){
                        addNext(q, r1, c1, nr, c1, cur.cnt+1);
                        addNext(q, r2, c2, nr, c2, cur.cnt+1);
                    }
                }
            }else{ // 세로 
                for(int d : rotate){
                    int nc = c1 + d;
                    
                    if(isEmpty(r1, nc) && isEmpty(r2, nc)){
                        addNext(q, r1, c1, r1, nc, cur.cnt+1);
                        addNext(q, r2, c2, r2, nc, cur.cnt+1);
                    }
                }
            }
        }
        
        return -1;
    }
    
    public void addNext(Queue<Robot> q, int r1, int c1, int r2, int c2, int cnt){
        if(!isEmpty(r1, c1) || !isEmpty(r2, c2)) return;
        
        Robot next = new Robot(r1, c1, r2, c2, cnt);
        next.normalize();
        
        int dir = next.getDir();
        
        if(visited[next.r1][next.c1][dir]) return;
        
        visited[next.r1][next.c1][dir] = true;
        q.add(next);
    }
    
    public boolean isEmpty(int r, int c){
        return (r >= 0 && c >= 0 && r < n && c < n && board[r][c] == 0);
    }
    
    public class Robot{
        int r1, c1, r2, c2, cnt;
        public Robot(int r1, int c1, int r2, int c2, int cnt){
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
            this.cnt = cnt;
            normalize();
        }
        
        void normalize(){
            if(r1 > r2 || (r1 == r2 && c1 > c2)){
                int tr = r1;
                int tc = c1;
                
                r1 = r2;
                c1 = c2;
                
                r2 = tr;
                c2 = tc;
            }
        }
        
        int getDir(){
            if(r1 == r2) return 0; // 가로
            return 1; // 세로
        }
    }
}