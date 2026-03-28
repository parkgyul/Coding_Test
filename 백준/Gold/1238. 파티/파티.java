import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    static int N, M, X;
    static List<City>[] toX;
    static List<City>[] fromX;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        toX = new ArrayList[N+1];
        fromX = new ArrayList[N+1];

        for(int i = 1; i <= N; i++){
            toX[i] = new ArrayList<>();
            fromX[i] = new ArrayList<>();
        }

        for(int i =0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            fromX[start].add(new City(end, cost));
            toX[end].add(new City(start, cost));
        }

        int max = Integer.MIN_VALUE;

        int[] costFromX = dijkstra(X, fromX);
        int[]costToX = dijkstra(X, toX);

        for(int i = 1; i <= N; i++){
            max = Math.max(costFromX[i] + costToX[i], max);
        }

        System.out.print(max);
    }

    public static int[] dijkstra(int start, List<City>[] g){
        PriorityQueue<City> pq = new PriorityQueue<>();
        pq.add(new City(start, 0));
        int[] costs = new int[N+1];

        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[start] = 0;

        while(!pq.isEmpty()){
            City cur = pq.poll();

            if(cur.cost > costs[cur.num])
                continue;

            for(City next : g[cur.num]){
                int nextCost = cur.cost + next.cost;

                if(nextCost >= costs[next.num])
                    continue;

                costs[next.num] = nextCost;

                pq.add(new City(next.num, nextCost));
            }
        }

        return costs;
    }

    public static class City implements Comparable<City>{
        int num, cost;
        public City(int num, int cost){
            this.num = num;
            this.cost = cost;
        }

        public int compareTo(City o){
            return this.cost - o.cost;
        }
    }
}