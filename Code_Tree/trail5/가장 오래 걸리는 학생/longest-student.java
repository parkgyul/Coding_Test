import java.io.*;
import java.util.*;

public class Main {
    static int N, M, X;
    static List<Node>[] nodes;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nodes = new ArrayList[N+1];

        for(int i = 1; i <= N; i++){
            nodes[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int fr = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            nodes[fr].add(new Node(to, cost));
            nodes[to].add(new Node(fr, cost));
        }

        int[] costs = dijkstra(N);
        int max = 0;
        for(int i = 1; i <= N-1; i++){
            max = Math.max(max, costs[i]);
        }

        System.out.print(max);
    }

    static int[] dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        
        int[] costs = new int[N+1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[N] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(cur.c > costs[cur.n]) continue;

            for(Node next : nodes[cur.n]){
                int nextCost = next.c + cur.c;

                if(nextCost >= costs[next.n]) continue;

                costs[next.n] = nextCost;
                pq.add(new Node(next.n, nextCost));
            }
        }

        return costs;
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