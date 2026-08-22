import java.io.*;
import java.util.*;

public class Main {
    static List<Stop>[] stops;
    static int[] busFees;
    static int A, B, N;
    static long[][] dist;   // dist[stop][bus] = 그 버스를 "타고 있는 채로" 그 정류장에 도달하는 최소 비용
    static int[][] times;   // 위 최소 비용을 만족하는 최소 시간
    static long INF = (long) 1e12 + 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        stops = new ArrayList[1001];
        for (int i = 1; i <= 1000; i++) stops[i] = new ArrayList<>();
        busFees = new int[N + 1];

        for (int bus = 1; bus <= N; bus++) {
            st = new StringTokenizer(br.readLine());
            int fee = Integer.parseInt(st.nextToken());
            busFees[bus] = fee;
            int num = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int prev = Integer.parseInt(st.nextToken());
            for (int i = 1; i < num; i++) {
                int next = Integer.parseInt(st.nextToken());
                stops[prev].add(new Stop(next, bus));
                prev = next;
            }
        }

        if (A == B) {
            System.out.print("0 0");
            return;
        }

        dist = new long[1001][N + 1];
        times = new int[1001][N + 1];
        for (long[] row : dist) Arrays.fill(row, INF);

        dijkstra();

        long bestCost = INF;
        int bestTime = Integer.MAX_VALUE;
        for (int bus = 1; bus <= N; bus++) {
            if (dist[B][bus] < bestCost) {
                bestCost = dist[B][bus];
                bestTime = times[B][bus];
            } else if (dist[B][bus] == bestCost && dist[B][bus] < INF) {
                bestTime = Math.min(bestTime, times[B][bus]);
            }
        }

        System.out.print(bestCost >= INF ? "-1 -1" : bestCost + " " + bestTime);
    }

    static void dijkstra() {
        PriorityQueue<Bus> pq = new PriorityQueue<>();

        for (Stop s : stops[A]) {
            long cost = busFees[s.busNum];
            if (cost < dist[s.next][s.busNum]) {
                dist[s.next][s.busNum] = cost;
                times[s.next][s.busNum] = 1;
                pq.add(new Bus(s.busNum, s.next, cost, 1));
            }
        }

        while (!pq.isEmpty()) {
            Bus cur = pq.poll();

            // (정류장, 버스) 조합 기준으로 stale 체크
            if (dist[cur.stop][cur.num] < cur.cost) continue;
            if (dist[cur.stop][cur.num] == cur.cost && times[cur.stop][cur.num] < cur.time) continue;

            for (Stop next : stops[cur.stop]) {
                long nextCost = cur.cost + (next.busNum == cur.num ? 0 : busFees[next.busNum]);
                int nextTime = cur.time + 1;

                if (nextCost > dist[next.next][next.busNum]) continue;
                if (nextCost == dist[next.next][next.busNum] && nextTime >= times[next.next][next.busNum]) continue;

                dist[next.next][next.busNum] = nextCost;
                times[next.next][next.busNum] = nextTime;
                pq.add(new Bus(next.busNum, next.next, nextCost, nextTime));
            }
        }
    }

    static class Stop {
        int next, busNum;
        Stop(int next, int busNum) { this.next = next; this.busNum = busNum; }
    }

    static class Bus implements Comparable<Bus> {
        int num, stop, time;
        long cost;
        Bus(int num, int stop, long cost, int time) {
            this.num = num; this.stop = stop; this.cost = cost; this.time = time;
        }
        public int compareTo(Bus o) {
            if (this.cost == o.cost) return this.time - o.time;
            return Long.compare(this.cost, o.cost);
        }
    }
}