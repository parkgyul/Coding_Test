import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.IOException;

public class Main{
    static int V, E;
    static List<Node>[] nodes;
    static boolean[] visited;
    static long total;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        nodes = new ArrayList[V+1];
        for(int i = 0; i < V+1; i++){
            nodes[i] = new ArrayList<>();
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            nodes[s].add(new Node(e, c));
            nodes[e].add(new Node(s, c));
        }

        visited = new boolean[V+1];

        total = 0;

        bfs();

        System.out.print(total);
    }

    public static void bfs(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            int cur_node = cur.node;
            long cur_cost = cur.cost;

            if(visited[cur_node])
                continue;

            visited[cur_node] = true;
            total += cur_cost;

            for(Node next : nodes[cur_node]){
                if(!visited[next.node])
                    pq.add(new Node(next.node, next.cost));
            }

        }
    }

    public static class Node implements Comparable<Node>{
        int node;
        long cost;

        public Node(int node, long cost){
            this.node = node;
            this.cost = cost;
        }

        public int compareTo(Node o){
            return Long.compare(this.cost, o.cost);
        }
    }

}