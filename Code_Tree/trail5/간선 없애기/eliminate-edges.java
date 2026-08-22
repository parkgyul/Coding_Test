import java.io.*;
import java.util.*;

public class Main {
    static int[][] distance;
    static int[] paths;
    static int N, M;
    static final int INF = (int)1e9;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        distance = new int[N+1][N+1];

        for(int i = 1; i <= N; i++){
            Arrays.fill(distance[i], INF);
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int fr = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int co = Integer.parseInt(st.nextToken());

            distance[fr][to] = co;
            distance[to][fr] = co;
        }

        paths = new int[N+1];

        int originCost = dijkstra();

        int idx = N;
        List<Integer> vertices = new ArrayList<>();
        vertices.add(idx);
        while(idx != 1){
            idx = paths[idx];
            vertices.add(idx);
        }

        int cnt = 0;

        for(int i = vertices.size()-1; i >= 1; i--){
            int fr = vertices.get(i);
            int to = vertices.get(i-1);
            int originValue = distance[fr][to];

            distance[fr][to] = INF;
            distance[to][fr] = INF;

            int cost = dijkstra();

            if(originCost != cost) cnt++;

            distance[fr][to] = originValue;
            distance[to][fr] = originValue;
        }

        System.out.print(cnt);
    }

    static int dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        int[] costs = new int[N+1];
        Arrays.fill(costs, INF);
        costs[1] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(cur.cost > costs[cur.node]) continue;

            for(int next = 1; next <= N; next++){
                if(distance[cur.node][next] == INF) continue;

                int nextCost = distance[cur.node][next] + cur.cost;

                if(nextCost >= costs[next]) continue;

                costs[next] = nextCost;
                paths[next] = cur.node;
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