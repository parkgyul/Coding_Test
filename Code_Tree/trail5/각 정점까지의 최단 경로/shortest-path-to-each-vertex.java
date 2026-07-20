import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Node>[] nodes;
    static int[] costs;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        nodes = new ArrayList[N+1];

        for(int i = 1; i <= N; i++){
            nodes[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            nodes[s].add(new Node(e, cost));
            nodes[e].add(new Node(s, cost));
        }

        costs = new int[N+1];
        Arrays.fill(costs, Integer.MAX_VALUE);

        dijkstra(K);

        for(int i = 1; i <= N; i++){
            System.out.println(costs[i] == Integer.MAX_VALUE ? -1 : costs[i]);
        }

    }

    static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        costs[start] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            
            if(costs[cur.node] < cur.cost){
                continue;
            }

            costs[cur.node] = cur.cost;

            for(Node next : nodes[cur.node]){
                int nextCost = next.cost + cur.cost;

                if(nextCost >= costs[next.node]) continue;

                costs[next.node] = nextCost;
                pq.add(new Node(next.node, nextCost));
            }
        }
    }

    static class Node implements Comparable<Node>{
        int node, cost;
        Node(int node, int cost){
            this.node = node;
            this.cost = cost;
        }

        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }
}