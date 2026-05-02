import java.util.*;
class Solution {
    static String answer;
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, -1, 1, 0};
    static boolean flag;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        answer = "impossible";
        
        int minDist = Math.abs(x-r) + Math.abs(y-c);
        if(minDist > k) return answer;
        if((k - minDist) % 2 == 1) return answer;
        
        dfs(k, 0, n, m, x, y, r, c, new char[k]);
        return answer;
    }
    
    public void dfs(int k, int depth, int n, int m, int si, int sj, int ei, int ej, char[] arr){
        if(flag) return;
        
        int remainMove = k - depth;
        int remainDist = Math.abs(si - ei) + Math.abs(sj - ej);
        
        if(remainDist > remainMove) return;
        if((remainDist - remainMove) % 2 == 1) return;
        
        if(depth == k){
            if(si == ei && sj == ej){
                answer = new String(arr);
                flag = true;
            }
            
            return;
        }
        
        for(int i =0 ; i < 4; i++){
            int ni = si + dx[i];
            int nj = sj + dy[i];
            
            if(ni < 1 || nj < 1 || ni > n || nj > m) continue;
            
            char ch;
            if(i == 0) ch = 'd';
            else if(i == 1) ch = 'l';
            else if(i == 2) ch = 'r';
            else ch = 'u';
            
            arr[depth] = ch;
            dfs(k, depth+1, n, m, ni, nj, ei, ej, arr);
            if(flag) return;
        }
    }
}