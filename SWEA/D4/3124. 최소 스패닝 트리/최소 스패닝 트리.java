import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

class Solution
{
    static int V;
    static long total;
    static List<Node>[] nodes;
    static boolean[] visited;
    static int[] costs;
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
		for(int test_case = 1; test_case <= T; test_case++)
		{
            sb.append("#").append(test_case).append(" ");
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            
            visited = new boolean[V+1];
            costs = new int[V+1];
            nodes = new ArrayList[V+1];
            for(int i = 1; i <V+1; i++){
                nodes[i] = new ArrayList<>();
                costs[i] = Integer.MAX_VALUE;
            }
            
            for(int i = 0; i < E; i++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                
                nodes[start].add(new Node(end, cost));
                nodes[end].add(new Node(start, cost));
            }

            total = 0;
            costs[1] = 0;
            find();
            
            sb.append(total).append("\n"); 
		}
        System.out.print(sb);
	}
    
    public static void find(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,0));
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            int cur_index = cur.index;
            
            if(visited[cur_index]) 
                continue;
            
            visited[cur_index] = true;
            total += cur.cost;
            for(Node next : nodes[cur_index]){
                int next_index = next.index;
                
                if(visited[next_index]) continue;
                
                costs[next_index] = next.cost;
                pq.add(new Node(next_index, next.cost)); 
            }
        }
        
    }
    public static class Node implements Comparable<Node>{
        int index, cost;
        
        public Node(int index, int cost){
            this.index = index;
            this.cost = cost;
        }
        
        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }
    
}