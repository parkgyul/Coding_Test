import java.util.*;

class Solution {
    static List<Integer>[] list;
    static int N;
    static int[] costs;
    static int max = 0, maxCnt = 0;
    public int solution(int n, int[][] edge) {
        int answer = 0;
        N = n;
        
        list = new ArrayList[n+1];
        
        for(int i = 1; i <= n; i++){
            list[i] = new ArrayList<>();
        }
        
        for(int[] e : edge){
            list[e[0]].add(e[1]);
            list[e[1]].add(e[0]);
        }
        
        costs = new int[n+1];
        
        bfs();
        
        return maxCnt;
    }
    
    static void bfs(){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{1, 0});
        boolean[] visited = new boolean[N+1];
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            if(visited[cur[0]]) continue;
            
            visited[cur[0]] = true;
            costs[cur[0]] = cur[1];
            if(max < cur[1]){
                max = cur[1];
                maxCnt = 1;
            }else if(max == cur[1]){
                maxCnt++;
            }
            
            for(int next : list[cur[0]]){
                if(visited[next]) continue;
                
                q.add(new int[]{next, cur[1] + 1});
            }
        }
        
    }
}