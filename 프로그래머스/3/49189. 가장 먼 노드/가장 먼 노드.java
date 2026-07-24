import java.util.*;

class Solution {
    static List<Integer>[] list;
    static int[] distance;
    public int solution(int n, int[][] edge) {
        int max = 0;
        int cnt = 1;
        list = new ArrayList[n+1];
        
        for(int i = 0; i < n+1; i++){
            list[i] = new ArrayList<>();
        }
        
        distance = new int[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;
        
        for(int[] e : edge){
            list[e[0]].add(e[1]);
            list[e[1]].add(e[0]);
        }
        
        dfs();
        
        for(int i = 1; i <= n; i++){
            if(max < distance[i]){
                max = distance[i];
                cnt = 1;
            }else if(max == distance[i]){
                cnt ++;
            }
        }
        
        return cnt;
    }
    static void dfs(){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(1, 0));
        
        while(!q.isEmpty()){
            Node cur = q.poll();
            
            if(cur.d > distance[cur.n]) continue;
            
            for(int next : list[cur.n]){
                if(cur.d + 1 >= distance[next]) continue;
                
                distance[next] = cur.d + 1;
                q.add(new Node(next, cur.d+1));
            }
        }
    }
    
    static class Node{
        int n, d;
        
        Node(int n, int d){
            this.n = n;
            this.d = d;
        }
    }
}