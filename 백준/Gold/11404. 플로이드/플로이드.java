import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.io.InputStreamReader;

public class Main{
    static int n, m;
    static List<Bus>[] buses;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args)throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        StringTokenizer st;

        buses = new ArrayList[n+1];
        for(int i = 0; i < n+1; i++){
            buses[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            buses[start].add(new Bus(end, cost));
        }

        for(int i = 1; i <= n; i++){
            bfs(i);
        }

        System.out.print(sb);
    }

    public static void bfs(int start){
        PriorityQueue<Bus> q = new PriorityQueue<>();
        q.add(new Bus(start, 0));
        long[] costs = new long[n+1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[start] = 0;

        while(!q.isEmpty()){
            Bus cur = q.poll();

            if(cur.cost > costs[cur.city])
                continue;

            for(Bus next : buses[cur.city]){
                long next_cost = costs[cur.city] + next.cost;

                if(next_cost > costs[next.city])
                    continue;

                costs[next.city] = next_cost;
                q.add(new Bus(next.city, next_cost));
            }
        }

        for(int i = 1; i < n+1; i++){
            sb.append(costs[i] == Integer.MAX_VALUE ? 0 : costs[i]).append(" ");
        }

        sb.append("\n");
    }

    public static class Bus implements Comparable<Bus>{
        int city; long cost;
        public Bus(int city, long cost){
            this.city = city;
            this.cost = cost;
        }
        
        public int compareTo(Bus o){
            return Long.compare(this.cost, o.cost);
        }
    }
}