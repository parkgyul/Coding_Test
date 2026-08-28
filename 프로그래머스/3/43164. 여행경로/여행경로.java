import java.util.*;

class Solution {
    static String[] path;
    static int n;
    static boolean flag = false;
    static boolean[] visited;
    
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        
        n = tickets.length;
        
        Arrays.sort(tickets, (a, b) -> {
           if(a[0].equals(b[0])) return a[1].compareTo(b[1]);
              return a[0].compareTo(b[0]);
        });
        
        visited = new boolean[n];
    
        path = new String[n+1];
        path[0] = "ICN";
        
        dfs(0, "ICN", tickets);
    
        return path;
    }
    
    static void dfs(int depth, String city, String[][] tickets){
        if(depth == n){
            flag = true;
            return;
        }
        
        if(flag) return;
        
        for(int i = 0; i < n; i++){
            if(visited[i] || !tickets[i][0].equals(city)) continue;
            
            visited[i] = true;
            path[depth+1] = tickets[i][1];
            dfs(depth+1, tickets[i][1], tickets);
            
            if(flag) return;
            visited[i] = false;
        }
    }
}