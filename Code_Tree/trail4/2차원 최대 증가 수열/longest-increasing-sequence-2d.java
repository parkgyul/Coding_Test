import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] arr;
    static int[][] dp;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        dp = new int[N][M];

        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = Integer.MIN_VALUE;
            }
        }
        dp[0][0] = 1;

        int answer = 1;
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(0, 0, 1));

        while(!pq.isEmpty()){
            Point cur = pq.poll();

            if(dp[cur.i][cur.j] > cur.cnt) continue;

            for(int i = cur.i+1; i < N; i++){
                for(int j = cur.j+1; j < M; j++){
                    if(arr[i][j] <= arr[cur.i][cur.j]) continue;

                    if(dp[cur.i][cur.j]+1 <= dp[i][j]) continue;

                    dp[i][j] = dp[cur.i][cur.j] + 1;
                    answer = Math.max(dp[i][j], answer);
                    pq.add(new Point(i, j, dp[i][j]));
                }
            }
        }

        System.out.print(answer);

    }

    static class Point implements Comparable<Point>{
        int i, j, cnt;

        Point(int i, int j, int cnt){
            this.i = i;
            this.j = j;
            this.cnt = cnt;
        }

        public int compareTo(Point o){
            return o.cnt - this.cnt;
        }
    }
}