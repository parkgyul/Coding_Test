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

        for(int i = 1; i <= N; i++){
            for(int cnt = 0; cnt <= Math.min(K, i); cnt++){
                if(arr[i] >= 0){// 양수 라면
                    dp[i][0] = Math.max(dp[i][0], arr[i]);
                    answer = Math.max(answer, dp[i][0]);

                    if(dp[i-1][cnt] != -INF){
                        dp[i][cnt] = Math.max(dp[i-1][cnt] + arr[i], dp[i][cnt]);
                         answer = Math.max(answer, dp[i][cnt]);
                    }
                }else{ // 음수 라면
                    dp[i][1] = Math.max(dp[i][1], arr[i]);
                    answer = Math.max(answer, dp[i][1]);

                    if(cnt >0 && dp[i-1][cnt-1] != -INF){
                        dp[i][cnt] = Math.max(dp[i-1][cnt-1] + arr[i], dp[i][cnt]);
                         answer = Math.max(answer, dp[i][cnt]);
                    }

                }
            }
        }


        System.out.print(answer);
    }
}