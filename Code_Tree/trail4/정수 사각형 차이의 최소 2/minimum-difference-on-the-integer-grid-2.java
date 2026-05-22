import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int N;
    static int[][] map;
    static int[][] dp;
    static int minValue = INF;
    static int maxValue = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                minValue = Math.min(minValue, map[i][j]);
                maxValue = Math.max(maxValue, map[i][j]);
            }
        }

        int answer = INF;

        for(int lowBound = minValue; lowBound <= maxValue; lowBound++){
            int upperBound = solve(lowBound);

            if(upperBound == INF) continue;

            answer = Math.min(answer, upperBound - lowBound);
        }

        System.out.print(answer);
    }

    static int solve(int lowBound){
        if(map[0][0] < lowBound) return INF;

        for(int i = 0; i < N; i++) {
            Arrays.fill(dp[i], INF);
        }

        dp[0][0] = map[0][0];
        
        for(int i = 1; i < N; i++){
            if(map[i][0] >= lowBound && map[i-1][0] != INF){
                dp[i][0] = Math.max(dp[i-1][0], map[i][0]);
            }
        }

        for(int j = 1; j < N; j++){
            if(map[0][j] >= lowBound && map[0][j-1] != INF){
                dp[0][j] = Math.max(dp[0][j-1], map[0][j]);
            }
        }

        for(int i = 1; i < N; i++){
            for(int j = 1; j < N; j++){
                if(map[i][j] < lowBound) continue;

                int min = Math.min(dp[i-1][j], dp[i][j-1]);

                if(min == INF) continue;
                dp[i][j] = Math.max(min, map[i][j]);
            }
        }

        return dp[N-1][N-1];
    }
}