import java.util.*;

class Solution {
    static int n, m;
    static boolean[][][] visited;
    public int solution(int[][] board) {
        int answer = 0;
        n = board.length;
        m = board[0].length;

        return bfs(board);
    }
    
    static int bfs(int[][] board){
        visited = new boolean[board.length][board[0].length][2];
        visited[0][0][0] = true;
        
        Queue<Robot> q = new LinkedList<>();
        q.add(new Robot(0, 0, 0, 1, 0));
        
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        
        int[] dr = {1, -1};
        
        while(!q.isEmpty()){
            Robot cur = q.poll();
            
            int si = cur.si;
            int sj = cur.sj;
            int ei = cur.ei;
            int ej = cur.ej;
            
            if(ei == board.length-1 && ej== board[0].length-1)
                return cur.cnt;
            
            for(int i = 0; i < 4; i++){ // 그냥 이동
                int nsi = cur.si + dx[i];
                int nsj = cur.sj + dy[i];
                int nei = cur.ei + dx[i];
                int nej = cur.ej + dy[i];
                
                if(!canGo(nsi, nsj, board) || !canGo(nei, nej, board)) 
                    continue;
                
                if(visited[nsi][nsj][getDir(nsi, nei)]) continue;
                
                // 그대로 이동
                visited[nsi][nsj][getDir(nsi, nei)] = true;
                q.add(new Robot(nsi, nsj, nei, nej, cur.cnt+1));
                
                if(getDir(cur.si, cur.ei) == 0){ // 가로
                    if(i == 0){
                        addNext(q, ei-1, ej-1, si, sj, cur.cnt+1);
                        addNext(q, si-1 , sj+1, ei, ej, cur.cnt+1);
                    }else if(i == 1){
                        addNext(q, ei+1, ej-1, si, sj, cur.cnt+1);
                        addNext(q, si+1 , sj+1, ei, ej, cur.cnt+1);
                    }
                }else{ // 세로
                     if(i == 2){
                        addNext(q, ei-1, ej+1, si, sj, cur.cnt+1);
                        addNext(q, si+1 , sj+1, ei, ej, cur.cnt+1);
                    }else if(i == 3){
                        addNext(q, ei-1, ej-1, si, sj, cur.cnt+1);
                        addNext(q, si+1 , sj-1, ei, ej, cur.cnt+1);
                    }
                }
            }
        }
        return -1;
    }
    
    static void addNext(Queue<Robot> q, int si, int sj, int ei, int ej, int cnt){
        Robot next = new Robot(si, sj, ei, ej, cnt);
        next.change();
        
        if(visited[next.si][next.sj][getDir(si, ei)]) return;
        
        visited[next.si][next.sj][getDir(si, ei)] = true;
        q.add(next);
    }
    
    static boolean canGo(int ni, int nj, int[][] board){
        return (ni >= 0 && ni < n && nj >= 0 && nj < m && board[ni][nj] == 0);
    }
    
    static int getDir(int si, int ei){
        if(si == ei) return 0;
        else return 1;
    }
    
    
    static class Robot{
        int si, sj, ei, ej, cnt;
        
        Robot(int si, int sj, int ei, int ej, int cnt){
            this.si = si;
            this.sj = sj;
            this.ei = ei;
            this.ej = ej;
            this.cnt = cnt;
        }
        
        public void change(){
            if((si == ei && sj > ej) || si > ei){
                int tempI = si;
                int tempJ = sj;
                
                si = ei;
                sj = ej;
                ei = tempI;
                ej = tempJ;
            }
        }
    }
}