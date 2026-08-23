import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); 
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[][][] dp = new int[N][5][M+1];

        for(int i = 1; i <= 4; i++){
            dp[0][i][0] = (arr[0] == i ? 1: 0);
        }

        for(int i = 1; i < N; i++){
            for(int j = 1; j <= 4; j++){
                for(int k = 0; k <= M; k++){
                    for(int p = 1; p <= 4; p++){
                        if(j != p && k < M){
                            dp[i][p][k+1] = Math.max(dp[i-1][j][k] + (arr[i] == p ? 1: 0), dp[i][p][k+1]); 
                        }else if(j == p){
                            dp[i][p][k] = Math.max(dp[i-1][j][k] + (arr[i] == p ? 1: 0), dp[i][p][k]); 
                        }
                    }
                }
            }
        }

        int max = 0;
        for(int j = 1; j <= 4; j++){
            for(int k = 0; k <= M; k++){
                if(max < dp[N-1][j][k]) max = dp[N-1][j][k];
            }
        }

        System.out.print(max);
    }
}