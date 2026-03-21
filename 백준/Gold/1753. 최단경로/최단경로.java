import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.IOException;

public class Main{
    static int V;
    static int[] costs;
    static List<Node>[] nodes;
    static int start;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        costs = new int[V+1];
        start = Integer.parseInt(br.readLine());

        nodes = new ArrayList[V+1];
        for(int i = 1 ; i < V+1; i++){
            nodes[i] = new ArrayList<>();
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            nodes[s].add(new Node(e,c));
        }

        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[start] = 0;
        bfs();

        for(int i = 1; i < V+1; i++){
            System.out.println(costs[i] == Integer.MAX_VALUE ? "INF" : costs[i]);
        }
    }
    public static void bfs(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            int cur_cost = cur.cost;
            int cur_node = cur.node;

            if(cur_cost > costs[cur_node])
                continue;
            
            for(Node next : nodes[cur_node]){
                int next_cost = next.cost + cur_cost;

                if(next_cost >= costs[next.node])
                    continue;

                costs[next.node] = next_cost;
                pq.add(new Node(next.node, next_cost));
            }
        }
    }

    public static class Node implements Comparable<Node>{
        int node, cost;
        public Node(int node, int cost){
            this.node = node;
            this.cost = cost;
        }

        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }
}