import java.util.*;

class Solution {
    static int[] costs;
    static List<Integer>[] list;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        costs = new int[n+1];
        list = new ArrayList[n+1];
        
        Arrays.fill(costs, -1);
        
        for(int i = 1; i <= n; i++){
            list[i] = new ArrayList<>();
        }
        
        for(int[] road : roads){
            list[road[0]].add(road[1]);
            list[road[1]].add(road[0]);
        }
        
        bfs(destination);
        
        int[] result = new int[sources.length];
        for(int i = 0; i < sources.length; i++){
            result[i] = costs[sources[i]];
        }
        
        return result;
    }
    
    static void bfs(int des){
        Queue<int[]> q = new LinkedList<>();
        costs[des] = 0;
        q.add(new int[]{des, 0});
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            if(costs[cur[0]] < cur[1]) continue;
            
            
            for(int next : list[cur[0]]){
                if(costs[next] != -1 && costs[next] <= cur[1] + 1) continue;
                
                costs[next] = cur[1] + 1;
                q.add(new int[]{next, cur[1] + 1});
            }
        }
    }
}