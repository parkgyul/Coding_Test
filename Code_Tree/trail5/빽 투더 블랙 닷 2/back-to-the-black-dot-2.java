import java.io.*;
import java.util.*;

public class Main {

    static int N, M;

    static List<Node>[] nodes;

    static final long INF = Long.MAX_VALUE / 4;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int red1 = Integer.parseInt(st.nextToken());
        int red2 = Integer.parseInt(st.nextToken());

        nodes = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }

        long between = INF;

        for (int t = 0; t < M; t++) {

            st = new StringTokenizer(br.readLine());

            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            long d = Long.parseLong(st.nextToken());

            nodes[i].add(new Node(j, d));
            nodes[j].add(new Node(i, d));

            if ((i == red1 && j == red2) || (i == red2 && j == red1)) {
                between = Math.min(between, d);
            }
        }

        long[] red1Cost = dijkstra(red1);
        long[] red2Cost = dijkstra(red2);

        long result = INF;

        for (int i = 1; i <= N; i++) {

            if (i == red1 || i == red2) {
                continue;
            }

            if (between < INF) {
                result = Math.min(
                        result,
                        2L * between + 2L * Math.min(red1Cost[i], red2Cost[i])
                );
            }

            result = Math.min(
                    result,
                    2L * (red1Cost[i] + red2Cost[i])
            );
        }

        System.out.print(result == INF ? -1 : result);
    }

    static long[] dijkstra(int start) {

        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(start, 0));

        long[] costs = new long[N + 1];

        Arrays.fill(costs, INF);

        costs[start] = 0;

        while (!pq.isEmpty()) {

            Node cur = pq.poll();

            if (costs[cur.node] < cur.cost) {
                continue;
            }

            for (Node next : nodes[cur.node]) {

                long nextCost = cur.cost + next.cost;

                if (nextCost >= costs[next.node]) {
                    continue;
                }

                costs[next.node] = nextCost;

                pq.add(new Node(next.node, nextCost));
            }
        }

        return costs;
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
}