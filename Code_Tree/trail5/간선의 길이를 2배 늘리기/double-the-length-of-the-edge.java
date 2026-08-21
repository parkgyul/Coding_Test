import java.io.*;
import java.util.*; 

public class Main {
    static int N, M;
    static int[][] distance;
    static final int INF = Integer.MAX_VALUE;
    static int[] path;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        distance = new int[N+1][N+1];

        for(int i = 1; i <= N; i++){
            Arrays.fill(distance[i], INF);
        }

        path = new int[N+1];
    
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int fr = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int co = Integer.parseInt(st.nextToken());

            distance[fr][to] = co;
            distance[to][fr] = co;
        }

        int max = Integer.MIN_VALUE;

        int first = dijkstra();

        int idx = N;
        ArrayList<Integer> vertices = new ArrayList<>();
        vertices.add(idx);
        while(idx != 1){
            idx = path[idx];
            vertices.add(idx);
        }

        for(int i = vertices.size()-1; i >= 1; i--){
            int fr = vertices.get(i);
            int to = vertices.get(i-1);

            distance[fr][to] *= 2;
            distance[to][fr] *= 2;

            int value = dijkstra();

            max = Math.max(max, value - first);

            distance[fr][to] /= 2;
            distance[to][fr] /= 2;
        }


        System.out.print(max);
    }

    static int dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        int[] costs = new int[N+1];

        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[1] = 0;
        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(costs[cur.node] < cur.cost) continue;

            for(int next = 1; next <= N; next++){
                if(cur.node == next) continue;

                if(distance[cur.node][next] == INF) continue;

                int nextCost = distance[cur.node][next] + cur.cost;

                if(nextCost >= costs[next]) continue;

                costs[next] = nextCost;
                path[next] = cur.node;
                pq.add(new Node(next, nextCost));
            }
        }

        return costs[N];
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