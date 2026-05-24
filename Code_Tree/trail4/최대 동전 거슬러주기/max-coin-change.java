import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        final int INF = Integer.MIN_VALUE;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] coin = new int[N];
        int[] dp = new int[M+1];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            coin[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(dp, INF);
        dp[0] = 0;

        for(int i = 1; i <= M; i++){
            for(int j = 0; j < N; j++){
                if(i - coin[j] >= 0 && dp[i-coin[j]] != INF){
                    dp[i] = Math.max(dp[i-coin[j]] + 1, dp[i]);
                }
            }
        }

        System.out.print(dp[M] == INF ? -1 : dp[M]);
    }
}