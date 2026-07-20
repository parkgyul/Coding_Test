import java.io.*;
import java.util.*;

public class Main {
    static List<Node>[] nodes;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        nodes = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) nodes[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            nodes[s].add(new Node(e, c));
            nodes[e].add(new Node(s, c)); // 양방향
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int[] distFromStart = dijkstra(start, N);
        int[] distFromEnd = dijkstra(end, N);

        int total = distFromStart[end];

        StringBuilder sb = new StringBuilder();
        sb.append(total).append("\n");

        List<Integer> path = new ArrayList<>();
        path.add(start);
        int cur = start;

        while (cur != end) {
            int next = -1;
            for (Node cand : nodes[cur]) {
                if (distFromStart[cur] + cand.cost + distFromEnd[cand.node] == total) {
                    if (next == -1 || cand.node < next) {
                        next = cand.node;
                    }
                }
            }
            path.add(next);
            cur = next;
        }

        for (int i = 0; i < path.size(); i++) {
            sb.append(path.get(i));
            if (i != path.size() - 1) sb.append(" ");
        }

        System.out.print(sb);
    }

    static int[] dijkstra(int start, int N) {
        int[] cost = new int[N + 1];
        Arrays.fill(cost, INF);
        cost[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cost[cur.node] < cur.cost) continue;

            for (Node next : nodes[cur.node]) {
                int nextCost = cur.cost + next.cost;
                if (nextCost < cost[next.node]) {
                    cost[next.node] = nextCost;
                    pq.add(new Node(next.node, nextCost));
                }
            }
        }
        return cost;
    }

    static class Node implements Comparable<Node> {
        int node, cost;
        Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}