import java.util.*;
class Solution {
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, -1, 1, 0};
    static String answer = "";
    static boolean flag;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {    
        int minDist = Math.abs(x-r) + Math.abs(y-c);
        
        if(k < minDist) return "impossible";
        if((k - minDist) % 2 != 0) return "impossible";
        
        flag = false;
        dfs(n, m, 0, new char[k], x-1, y-1, r-1, c-1, k);
        
        return answer;
    }
    
    public static void dfs(int n, int m, int cnt, char[] path, int nowX, int nowY, int r, int c, int k)
    {
        if(flag) return;
        if(cnt == k){
            if(nowX == r && nowY == c){
                answer = new String(path);
                flag = true;
            }
            
            return;
        }
        
        for(int i = 0; i < 4; i++){
            int ni = nowX + dx[i];
            int nj = nowY + dy[i];
            
            if(ni < 0 || nj < 0 || ni >= n || nj >= m) continue;
            
            int dist = Math.abs(ni - r) + Math.abs(nj - c);
            
            if(dist > k - cnt) continue;
            
            char ch;
            if(i == 0) ch = 'd';
            else if(i == 1) ch = 'l';
            else if(i == 2) ch = 'r';
            else ch = 'u';
            
            char[] newPath = path;
            newPath[cnt] = ch;
            dfs(n, m, cnt+1, newPath, ni, nj, r, c, k);
        }
    }    
}