import java.io.*;
import java.util.*; 

public class Main {
    static List<Stop>[] stops;
    static int[] busFees;
    static int A, B;
    static long[][] costs;
    static int[][] times;
    static long INF = (long) 1e12+1;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        stops = new ArrayList[1001]; // 이어진 정거장을 저장하기 위함.
        for(int i = 1; i <= 1000; i++){
            stops[i] = new ArrayList<>();
        }
        busFees = new int[N+1];

        for(int bus = 1; bus <= N; bus++){
            st = new StringTokenizer(br.readLine());
            int fee = Integer.parseInt(st.nextToken());
            busFees[bus] = fee;

            int num = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int prev = Integer.parseInt(st.nextToken());
            for(int i = 1; i < num; i++){
                int next = Integer.parseInt(st.nextToken());

                stops[prev].add(new Stop(next, bus));

                prev = next;
            }
        }

        costs = new long[1001][N+1];
        for(int i = 1; i <= 1000; i++){
            Arrays.fill(costs[i], INF);
        }

        times = new int[1001][N+1];

        dijkstra();

        long minCost = INF;
        int minTime = (int)1e9;
        for(int bus = 1; bus <= N; bus++){
            if(costs[B][bus] < minCost){
                minCost = costs[B][bus];
                minTime = times[B][bus];
            }else if(costs[B][bus] == minCost && times[B][bus] < minTime){
                minTime = times[B][bus];
            }
        }

        System.out.print(minCost == INF ? "-1 -1" : minCost + " " + minTime);
    }

    static void dijkstra(){
        PriorityQueue<Bus> pq = new PriorityQueue<>();
        
        for(Stop s : stops[A]){
            costs[A][s.busNum] = 0;
            pq.add(new Bus(s.busNum, s.next, busFees[s.busNum], 1));
            costs[s.next][s.busNum] = Math.min(costs[s.next][s.busNum], busFees[s.busNum]);
            times[s.next][s.busNum] = 1;
        }

        while(!pq.isEmpty()){
            Bus cur =  pq.poll();

            if(costs[cur.stop][cur.num] < cur.cost) continue;
            else if(costs[cur.stop][cur.num] == cur.cost && times[cur.stop][cur.num] < cur.time) continue;

            for(Stop next : stops[cur.stop]){
                long nextCost = cur.cost + (next.busNum == cur.num ?  0 : busFees[next.busNum]);

                if(nextCost > costs[next.next][next.busNum]) continue;

                if(nextCost < costs[next.next][next.busNum]){
                    costs[next.next][next.busNum] = nextCost;
                    times[next.next][next.busNum] = cur.time + 1;
                }else if(nextCost == costs[next.next][next.busNum] && cur.time+1 < times[next.next][next.busNum]){
                    times[next.next][next.busNum] = cur.time + 1;
                }

                pq.add(new Bus(next.busNum, next.next, nextCost, cur.time+1));
            }
        }
    }


    static class Stop{
        int next, busNum;

        Stop(int next, int busNum){
            this.next = next;
            this.busNum = busNum;
        }
    }

    static class Bus implements Comparable<Bus>{
        int num, stop, time;
        long cost;

        Bus(int num, int stop, long cost, int time){
            this.num = num;
            this.stop = stop;
            this.cost = cost;
            this.time = time;
        }


        public int compareTo(Bus o){
            if(this.cost == o.cost){
                return this.time - o.time;
            }

            return Long.compare(this.cost, o.cost);
        }
    }

}
