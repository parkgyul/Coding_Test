import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main{
    static List<Cow>[] cows;
    static int N, M;
    static int result;
    static boolean[] visited;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cows = new ArrayList[N+1];

        for(int i =0 ; i < N+1; i ++){
            cows[i] = new ArrayList<>();
        }


        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cow = Integer.parseInt(st.nextToken());
            cows[start].add(new Cow(end, cow));
            cows[end].add(new Cow(start, cow));
        }

        visited = new boolean[N+1];
        result = Integer.MAX_VALUE;
        bfs();

        System.out.println(result);

    }
    public static void bfs(){
        PriorityQueue<Cow> pq = new PriorityQueue<>();
        pq.add(new Cow(1, 0));

        while(!pq.isEmpty()){
            Cow cur = pq.poll();

            if(visited[cur.place])
                continue;

            if(cur.place == N){
                result = Math.min(result, cur.cost);
            }

            visited[cur.place] = true;

            for(Cow next : cows[cur.place]){
                int next_place = next.place;

                if(visited[next_place])
                    continue;

                pq.add(new Cow(next_place, cur.cost + next.cost));
            }
        }


    }
    public static class Cow implements Comparable<Cow>{
        int place, cost;
        public Cow(int place, int cost){
            this.place = place;
            this.cost = cost;
        }

        public int compareTo(Cow o){
            return this.cost - o.cost;
        }
    }
}