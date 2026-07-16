import java.util.*;
import java.io.*;

public class Main {
    static final int INF = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N+1][K+1];

        for(int i = 0; i <= N; i++){
            Arrays.fill(dp[i], -INF);
        }

        if(arr[1] >= 0){
            dp[1][0] = arr[1];
        }else{
            dp[1][1] = arr[1];
        }

        int answer = arr[1];

        for(int i = 2; i <= N; i++){
            if(arr[i] >= 0){ //양수 일떄
                dp[i][0] = Math.max(dp[i-1][0] + arr[i], arr[i]);
                answer = Math.max(answer, dp[i][0]);

                for(int j = 1; j <= K; j++){
                    if(dp[i-1][j] == -INF ) continue;
                    dp[i][j] = dp[i-1][j] + arr[i];
                    answer = Math.max(answer, dp[i][j]);
                }
            }else{
                dp[i][1] = Math.max(dp[i-1][0] + arr[i], arr[i]);
                answer = Math.max(answer, dp[i][1]);

                for(int j = 2; j <= K; j++){
                    if(dp[i-1][j-1] == -INF) continue;
                    dp[i][j] = dp[i-1][j-1] + arr[i];
                    answer = Math.max(answer, dp[i][j]);
                }
            }
        }


        System.out.print(answer);
    }
}