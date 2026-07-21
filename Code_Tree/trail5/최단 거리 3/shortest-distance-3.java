import java.io.*;
import java.util.*;

public class Main {
    static List<Node>[] nodes;
    static int[] costs;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        costs = new int[N+1];

        nodes = new ArrayList[N+1];
        for(int i =1; i <= N; i++){
            nodes[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            nodes[s].add(new Node(e, c));
            nodes[e].add(new Node(s, c));
        }

        st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        Arrays.fill(costs, Integer.MAX_VALUE);
        dijkstra(start, end);

        System.out.print(costs[end]);
    }

    static void dijkstra(int start, int end){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        costs[start] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(cur.cost > costs[cur.node]) continue;

            if(cur.node == end) return;

            for(Node next : nodes[cur.node]){
                int nextCost = next.cost + cur.cost;

                if(nextCost >= costs[next.node]){
                    continue;
                }

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