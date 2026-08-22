import java.io.*;
import java.util.*;

public class Main {
    static int X;
    static List<Node>[] nodes;
    static int[] costs;
    static int N, M;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        nodes = new ArrayList[N+1];

        for(int i = 0; i <= N; i++){
            nodes[i] = new ArrayList<>();
        }

        Set<Integer> cSet = new HashSet<>();

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int I = Integer.parseInt(st.nextToken());
            int J = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            nodes[I].add(new Node(J, L, C, 0));
            nodes[J].add(new Node(I, L, C, 0));

            cSet.add(C);
        }

        int result = Integer.MAX_VALUE;

        for(int minC : cSet){
            dijkstra(minC);
            result = Math.min(result, costs[N]);
        }

        System.out.print(result);
    }

    static void dijkstra(int minC){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        costs = new int[N+1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[1] = 0;

        pq.add(new Node(1, 0, Integer.MAX_VALUE, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(costs[cur.node] < cur.cost) continue;

            for(Node next : nodes[cur.node]){
                if(next.C > minC) continue;

                int nextL = next.L + cur.L; //B
                int nextC = Math.min(cur.C, next.C);  //A

                int nextCost = nextL + (X / nextC);
        
                if(nextCost >= costs[next.node]) continue;

                costs[next.node] = nextCost;
                pq.add(new Node(next.node, nextL, nextC, nextCost));
            }
        }
    }

    static class Node implements Comparable<Node>{
        int node, L, C;
        int cost;

        Node(int node, int L, int C, int cost){
            this.node = node;
            this.L = L;
            this.C = C;
            this.cost = cost;
        }

        public int compareTo(Node o){
            return Integer.compare(this.cost, o.cost) ;
        }
    }

}