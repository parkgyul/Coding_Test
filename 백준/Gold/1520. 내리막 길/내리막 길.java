import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Main{
    static int M, N;
    static int[][] arr, dp;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int cnt;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[M][N];

        for(int i = 0; i <M; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        dp = new int[M][N];
        for(int i = 0; i < M; i++){
            Arrays.fill(dp[i], -1);
        }

        System.out.print(dfs(0,0));

    }
    public static int dfs(int i, int j){
        if( i == M-1 && j == N-1) return 1;
        if(dp[i][j] != -1) return dp[i][j];

        dp[i][j] = 0;

        for(int a = 0; a < 4; a++){
            int ni = i + dx[a];
            int nj = j + dy[a];

            if(ni < 0 || ni >= M || nj < 0 || nj >= N)
                continue;

            if(arr[i][j] <=  arr[ni][nj])
                continue;

            dp[i][j] += dfs(ni, nj);
        }

        return dp[i][j];
    }

    public static class Point{
        int i, j;

        public Point(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
}