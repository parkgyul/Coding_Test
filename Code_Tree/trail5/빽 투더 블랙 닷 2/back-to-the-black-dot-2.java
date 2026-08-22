import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] arr;
    static int max = Integer.MIN_VALUE;
    static List<Node>[] nodes;
    static int INF = (int)1e9;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int red1 = Integer.parseInt(st.nextToken());
        int red2 = Integer.parseInt(st.nextToken());

        nodes = new ArrayList[N+1];
        
        for(int i = 1; i<= N; i++){
            nodes[i] = new ArrayList<>();
        }
        int between = INF;

        for(int t = 0; t < M; t++){
            st = new StringTokenizer(br.readLine());

            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            nodes[i].add(new Node(j, d));
            nodes[j].add(new Node(i, d));

            if((i == red1 && j == red2) || (i == red2 && j == red1)){
                between = Math.min(between, d);
            }
        }

        int[] red1Costs = dijkstra(red1);
        int[] red2Costs = dijkstra(red2);

        int result = INF;

        // for(int i = 1; i <= N; i++){
        //     System.out.print(red1Cost[i] + " ");
        // }
        // System.out.println();

        // for(int i = 1; i <= N; i++){
        //     System.out.print(red2Cost[i] + " ");
        // }
        //  System.out.println();
        for(int i = 1; i <= N; i++){
            
            if(i == red1 || i == red2) continue;

            if(red1Costs[i] != INF && red1Costs[red2] != INF && red2Costs[i] != INF){
                result = Math.min(result, red1Costs[i] + red1Costs[red2] + red2Costs[i]);
            }

            if(red2Costs[i] != INF && red2Costs[red1] != INF && red1Costs[i] != INF){
                result = Math.min(result, red2Costs[i] + red2Costs[red1] + red1Costs[i]);
            }
        }
        

        System.out.print(result == INF ? -1 : result);
    }

    

    static int[] dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        int[] costs = new int[N+1];
        Arrays.fill(costs, INF);
        costs[start] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(costs[cur.node] < cur.cost) continue;

            for(Node next : nodes[cur.node]){
                int nextCost = next.cost + cur.cost;

                if(nextCost >= costs[next.node]) continue;

                costs[next.node] = nextCost;
                pq.add(new Node(next.node, nextCost));
            }
        }

        return costs;
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