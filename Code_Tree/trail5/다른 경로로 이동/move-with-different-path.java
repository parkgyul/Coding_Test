import java.io.*;
import java.util.*;

public class Main {
    static List<int[]>[] nodes;
    static final int INF = Integer.MAX_VALUE;
    static int[] costs;       // 1번 기준 최단거리
    static int[] costsFromN;  // N번 기준 최단거리
    static int N, M;
    static boolean[] removed;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nodes = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) nodes[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int fr = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int co = Integer.parseInt(st.nextToken());

            nodes[fr].add(new int[]{to, co, i});
            nodes[to].add(new int[]{fr, co, i});
        }

        costs = new int[N + 1];
        costsFromN = new int[N + 1];
        removed = new boolean[M];

        dijkstra(1, costs);
        dijkstra(N, costsFromN);   // 간선 제거 전에 미리 계산해둬야 함

        markUsedEdges();

        dijkstra(1, costs);
        System.out.print(costs[N] == INF ? -1 : costs[N]);
    }

    static void markUsedEdges() {
        int total = costs[N];
        int cur = 1;

        while (cur != N) {
            int nextVertex = -1;
            int nextEdgeId = -1;

            for (int[] e : nodes[cur]) {
                if (removed[e[2]]) continue;
                int to = e[0], w = e[1];

                // cur->to 간선이 1~N 최단경로 "위"에 있는지 검증
                if (costs[cur] + w + costsFromN[to] == total) {
                    if (nextVertex == -1 || to < nextVertex) {
                        nextVertex = to;
                        nextEdgeId = e[2];
                    }
                }
            }

            removed[nextEdgeId] = true;
            cur = nextVertex;
        }
    }

    static void dijkstra(int start, int[] dist) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (dist[cur.node] < cur.cost) continue;

            for (int[] e : nodes[cur.node]) {
                if (removed[e[2]]) continue;
                int next = e[0];
                int nextCost = cur.cost + e[1];
                if (nextCost >= dist[next]) continue;
                dist[next] = nextCost;
                pq.add(new Node(next, nextCost));
            }
        }
    }

    static class Node implements Comparable<Node> {
        int node, cost;
        Node(int node, int cost) { this.node = node; this.cost = cost; }
        public int compareTo(Node o) { return this.cost - o.cost; }
    }
}