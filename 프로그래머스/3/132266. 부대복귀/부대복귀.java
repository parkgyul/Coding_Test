import java.util.*;
class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        
        List<Integer>[] roadInfos = new ArrayList[n+1];
        
        for(int i =1 ; i <= n; i++){
            roadInfos[i] = new ArrayList<>();
        }
        
        for(int[] road : roads){
            int from = road[0];
            int to = road[1];
            
            roadInfos[from].add(to);
            roadInfos[to].add(from);
        }
        
        
        int[] costs = dijkstra(roadInfos, n, destination);
        
        for(int i = 0; i < sources.length; i++){
            answer[i] = costs[sources[i]] == Integer.MAX_VALUE ? -1 : costs[sources[i]];
        }
        
        return answer;
    }
    
    public static int[] dijkstra(List<Integer>[] roadInfos, int n, int start){
        Queue<Road> q = new LinkedList<>();
        q.add(new Road(start, 0));
        int[] costs = new int[n+1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        
        while(!q.isEmpty()){
            Road cur = q.poll();
            
            if(costs[cur.node] < cur.cnt)
                continue;
            
            costs[cur.node] = cur.cnt;
            
            for(int next : roadInfos[cur.node]){
                int nextCost = cur.cnt + 1;
                
                if(costs[next] <= nextCost)
                    continue;
                
                q.add(new Road(next, nextCost));   
            }  
        }
    
        return costs;
    }
    
    
    public static class Road{
        int node, cnt;
        
        public Road(int node, int cnt){
            this.node = node;
            this.cnt = cnt;
        }
    }
}