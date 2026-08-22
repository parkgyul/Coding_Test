import java.io.*;
import java.util.*;

public class Main {
    static int[][] distance;
    static final int INF = Integer.MAX_VALUE;
    static int[] paths;
    static int N, M;
    static int[] costs;
    public static void main(String[] args) throws IOException{
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

    
        dijkstra(N);
        makePaths();
        dijkstra(1);
        System.out.print(costs[N] == INF ? -1 : costs[N]);
    }

    static void makePaths(){
        int idx = 1;

        List<Integer> vertices = new ArrayList<>();
        vertices.add(idx);

        while(idx != N){
            for(int x = 1; x <= N; x++){
                if(distance[idx][x] == INF) continue;

                if(costs[idx] == distance[x][idx] + costs[x]){ // 해당 지점까지 오는데 걸린 거리
                    idx = x;
                    vertices.add(idx);
                    break;
                }
            }
        }

        for(int i = vertices.size()-1; i >= 1; i--){
            int fr = vertices.get(i);
            int to = vertices.get(i-1);

            distance[fr][to] = INF;
            distance[to][fr] = INF;
        }
    }

    static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        costs = new int[N+1];

        Arrays.fill(costs, INF);
        costs[start] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(costs[cur.node] < cur.cost) continue;

            for(int next = 1; next <= N; next++){
                if(next == cur.node) continue;
                if(distance[cur.node][next] == INF) continue;

                int nextCost = cur.cost + distance[cur.node][next];

                if(nextCost >= costs[next]) continue;

                costs[next] = nextCost;
                pq.add(new Node(next, nextCost));
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
            if(this.cost == o.cost){
                return this.node - o.node;
            }

            return this.cost - o.cost;
        }
    }
}
