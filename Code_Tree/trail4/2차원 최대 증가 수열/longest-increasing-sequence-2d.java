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

        for(int i =0 ; i < N; i++){
            for(int j = 0; j < M; j++){

                for(int k = 0; k < i; k++){
                    for(int l = 0; l < j; l++){
                        if(dp[k][l] == Integer.MIN_VALUE) continue;

                        if(arr[k][l] < arr[i][j]){
                            dp[i][j] = Math.max(dp[k][l] + 1, dp[i][j]);
                        }
                    }
                }
                answer = Math.max(answer, dp[i][j]);
            }
        }

        System.out.print(answer);

    }
}