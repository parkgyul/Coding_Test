import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    static int N, M, X;
    static List<City>[] cities;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        cities = new ArrayList[N+1];

        for(int i = 1; i <= N; i++){
            cities[i] = new ArrayList<>();
        }

        for(int i =0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            cities[start].add(new City(end, cost));
        }

        int max = Integer.MIN_VALUE;
        for(int i = 1; i <= N; i++){
            if(i == X) continue;
            max = Math.max(calculateGo(i) + calculateCome(i), max);
        }


        System.out.print(max);
    }

    public static int calculateGo(int start){
        PriorityQueue<City> pq = new PriorityQueue<>();
        pq.add(new City(start, 0));
        boolean[] visited = new boolean[N+1];

        while(!pq.isEmpty()){
            City cur = pq.poll();

            if(visited[cur.num])
                continue;

            if(cur.num == X)
                return cur.cost;

            visited[cur.num] = true;

            for(City next : cities[cur.num]){
                if(visited[next.num])
                    continue;

                pq.add(new City(next.num, cur.cost+next.cost));
            }
        }

        return -1;
    }

    public static int calculateCome(int end){
        PriorityQueue<City> pq = new PriorityQueue<>();
        pq.add(new City(X, 0));
        boolean[] visited = new boolean[N+1];

        while(!pq.isEmpty()){
            City cur = pq.poll();

            if(visited[cur.num])
                continue;

            if(cur.num == end)
                return cur.cost;

            visited[cur.num] = true;

            for(City next : cities[cur.num]){
                if(visited[next.num])
                    continue;

                pq.add(new City(next.num, cur.cost+next.cost));
            }
        }
        return -1;
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