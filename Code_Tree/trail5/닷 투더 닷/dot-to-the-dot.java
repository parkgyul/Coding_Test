import java.io.*;
import java.util.*;

public class Main {

    static class Edge {
        int to;
        int l;
        int c;

        Edge(int to, int l, int c) {
            this.to = to;
            this.l = l;
            this.c = c;
        }
    }

    static class Node implements Comparable<Node> {
        int node;
        long cost;

        Node(int node, long cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    static int N, M;
    static long X;
    static List<Edge>[] graph;

    static long dijkstra(int minC) {
        long[] dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[1] = 0;
        pq.offer(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.cost != dist[cur.node]) {
                continue;
            }

            for (Edge edge : graph[cur.node]) {

                if (edge.c < minC) {
                    continue;
                }

                long nextCost = cur.cost + edge.l;

                if (nextCost < dist[edge.to]) {
                    dist[edge.to] = nextCost;
                    pq.offer(new Node(edge.to, nextCost));
                }
            }
        }

        return dist[N];
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st =
                new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Long.parseLong(st.nextToken());

        graph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        TreeSet<Integer> cValues = new TreeSet<>();

        for (int i = 0; i < M; i++) {

            st = new StringTokenizer(br.readLine());

            int I = Integer.parseInt(st.nextToken());
            int J = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph[I].add(new Edge(J, L, C));
            graph[J].add(new Edge(I, L, C));

            cValues.add(C);
        }

        long answer = Long.MAX_VALUE;

        for (int minC : cValues) {

            long B = dijkstra(minC);

            if (B == Long.MAX_VALUE) {
                continue;
            }

            long time = B + X / minC;

            answer = Math.min(answer, time);
        }

        System.out.println(answer);
    }
}