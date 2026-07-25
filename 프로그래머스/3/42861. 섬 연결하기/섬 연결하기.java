import java.util.*;

class Solution {
    static List<Node>[] nodes;
    static boolean[] checked;
    static int sum = 0;
    public int solution(int n, int[][] costs) {
        nodes = new ArrayList[n];
        for(int i = 0; i < n; i++){
            nodes[i] = new ArrayList<>();
        }
        
        for(int[] c : costs){
            nodes[c[0]].add(new Node(c[1], c[2]));
            nodes[c[1]].add(new Node(c[0], c[2]));
        }
        
        checked = new boolean[n];
        bfs();
        
        return sum;
    }
    static void bfs(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0));
        int cnt = 1;
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            
            if(checked[cur.n]) continue;
            
            checked[cur.n] = true;
            sum += cur.c;
            
            for(Node next : nodes[cur.n]){
                if(checked[next.n]) continue;
                
                pq.add(new Node(next.n, next.c));
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