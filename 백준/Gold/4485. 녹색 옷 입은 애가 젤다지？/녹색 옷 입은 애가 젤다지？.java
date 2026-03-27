import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.PriorityQueue;

public class Main{
    static int[][] arr;
    static int[][] costs;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int cnt = 1;
        while((N = Integer.parseInt(br.readLine())) != 0){
            arr = new int[N][N];
            costs = new int[N][N];

            for(int i = 0; i < N; i++){
                Arrays.fill(costs[i], Integer.MAX_VALUE);
            }
            

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bfs();

            sb.append("Problem ").append(cnt++).append(":").append(" ").append(costs[N-1][N-1]).append("\n");
        }

        System.out.print(sb);
    }

    public static void bfs(){
        PriorityQueue<Point> pq = new PriorityQueue<>();

        pq.add(new Point(0, 0, arr[0][0]));

        while(!pq.isEmpty()){
            Point cur = pq.poll();

            if(costs[cur.i][cur.j] < cur.cost)
                continue;

            for(int i = 0; i < 4; i++){
                int next_i = cur.i + dx[i];
                int next_j = cur.j + dy[i];

                if(next_i <0 || next_j < 0 || next_i >= N || next_j >= N)
                    continue;

                int next_cost = cur.cost + arr[next_i][next_j];

                if(next_cost >= costs[next_i][next_j])
                    continue;

                costs[next_i][next_j] = next_cost;
                pq.add(new Point(next_i, next_j, next_cost));
            }
        }

    }

    public static class Point implements Comparable<Point>{
        int i, j, cost;

        public Point(int i, int j, int cost){
            this.i = i;
            this.j = j;
            this.cost = cost;
        }

        public int compareTo(Point o){
            return this.cost - o.cost;
        }
    }
}