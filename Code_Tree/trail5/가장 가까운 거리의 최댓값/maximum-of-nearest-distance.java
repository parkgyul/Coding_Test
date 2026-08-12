import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Node>[] nodes;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        nodes = new ArrayList[N+1];
        for(int i = 0; i <= N; i++){
            nodes[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            nodes[to].add(new Node(from, distance));
            nodes[from].add(new Node(to, distance));
        }

        int[] dA = bfs(A);
        int[] dB = bfs(B);
        int[] dC = bfs(C);

        // for(int i = 1; i <= N; i++){
        //     System.out.print(dA[i]+ " ");
        // }

        int max = Integer.MIN_VALUE;

        for(int i = 1; i <= N; i++){
            if(i == A || i == B || i == C) continue;

            int min = Integer.MAX_VALUE;

            min = Math.min(dC[i], Math.min(dB[i], Math.min(min, dA[i])));

            max = Math.max(min, max);
        }

        System.out.print(max);

    }
    static int[] bfs(int start){
        int[] costs = new int[N+1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();

        costs[start] = 0;
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(costs[cur.n] < cur.d){
                continue;
            }

            for(Node node : nodes[cur.n]){
                int newCost = cur.d + node.d;

                if(costs[node.n] <= newCost) continue;

                costs[node.n] = newCost;
                pq.add(new Node(node.n, newCost));
            }
        }

        return costs;
    }

    static class Node implements Comparable<Node>{
        int n, d;

        Node(int n, int d){
            this.n = n;
            this.d = d;
        }

        public int compareTo(Node o){
            return this.d - o.d;
        }
    }
}