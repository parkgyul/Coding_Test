import java.util.*;

class Solution {
    static List<Node>[] nodes;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        
        nodes = new ArrayList[n+1];
        for(int i = 1; i < n+1; i++){
            nodes[i] = new ArrayList<>();
        }
        
        for(int[] f : fares){
            nodes[f[0]].add(new Node(f[1], f[2]));
            nodes[f[1]].add(new Node(f[0], f[2]));
        }
        
        int[] costA = new int[n+1], costB= new int[n+1], costS= new int[n+1];
        
        
        bfs(a, costA);
        bfs(b, costB);
        bfs(s, costS);
    
        for(int i = 1; i <= n; i++){
            answer = Math.min(answer, costS[i] + costA[i] + costB[i]);
        }
        answer = Math.min(answer, costS[a] + costS[b]); 
        
        return answer;
    }
    static void bfs(int start, int[] costs){
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[start] = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            
            if(cur.c > costs[cur.n]) continue;
            
            for(Node next : nodes[cur.n]){
                int nextCost = cur.c + next.c;
                
                if(nextCost >= costs[next.n]) continue;
                
                costs[next.n] = nextCost;
                pq.add(new Node(next.n, nextCost));
            }
        }
    }
    
    static class Node implements Comparable<Node>{
        int n, c;
        
        Node(int n, int c){
            this.n = n; 
            this.c = c;
        }
        
        public int compareTo(Node o){
            return this.c - o.c;
        }
    }
}