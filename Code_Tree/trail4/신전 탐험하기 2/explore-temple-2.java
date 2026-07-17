import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        final int INF = -100000000;

    
        int[][] floor = new int[N+1][4];

        StringTokenizer st;

        for(int i = 1; i < N+1; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= 3; j++){
                floor[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        for(int start = 1; start <= 3; start++){
            int[][] dp = new int[N+1][4];
            for(int i = 0; i < N+1; i++){
                Arrays.fill(dp[i], INF);
            }

            dp[1][start] = floor[1][start];

            for(int i = 2; i <= N; i++){
                for(int j = 1; j <= 3; j++){
                    for(int k = 1; k <= 3; k++){
                        if(j == k) continue;
                        if(dp[i-1][k] == INF) continue;

                        dp[i][j] = Math.max(dp[i][j], dp[i-1][k] + floor[i][j]);
                    }
                }
            }

            for(int i = 1; i <= 3; i++){
                if(i == start) continue;
                answer = Math.max(answer, dp[N][i]);
            }
        }

        System.out.print(answer);
        
    }
}