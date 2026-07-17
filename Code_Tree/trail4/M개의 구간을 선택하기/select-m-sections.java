import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 100000000;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int sum = 0;

        int[] arr = new int[N+1];
        int[][][] dp = new int[N+1][M+1][2];
        for(int i = 0; i <= N; i++){
            for(int j = 0; j <= M; j++){
                for(int k = 0; k < 2; k++){
                    dp[i][j][k] = -INF;
                }
            }
        }
        dp[0][0][0] = 0;

        for(int i = 1; i <= N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
    

        for(int i = 1; i < N+1; i++){
            for(int j = 0; j <= M; j++){
                // 선택하지 않음 -> 선택하지 않음
                dp[i][j][0] = Math.max(dp[i-1][j][1], dp[i-1][j][0]);

                // 선택함 -> 선택함
                dp[i][j][1] = Math.max(dp[i-1][j][1] + arr[i], dp[i][j][1]);


                // 선택하지 않음 -> 선택함.
                if(j-1 >= 0){
                    dp[i][j][1] = Math.max(dp[i-1][j-1][0]+ arr[i], dp[i][j][1]);
                }
            }
        }

        int answer = -INF;

        for(int i = 0; i < 2; i++){
            answer = Math.max(answer, dp[N][M][i]);
        }

        System.out.print(answer);


    }
}