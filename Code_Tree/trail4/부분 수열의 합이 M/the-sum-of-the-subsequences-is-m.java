import java.util.*; 
import java.io.*;

public class Main {
    public static void main(String[] args)throws IOException {
        final int INF = Integer.MAX_VALUE;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];
        int[] dp = new int[M+1];
        
        for(int i =0 ; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(dp, INF);
        dp[0] = 0;

        for(int i = 0; i < N; i++){
            for(int j = M; j >= 0; j--){
                if(j - arr[i] >= 0 && dp[j-arr[i]] != INF){
                    dp[j] = Math.min(dp[j], dp[j-arr[i]] + 1);
                }
            }
        }

        System.out.print(dp[M] == INF ? -1 : dp[M]);

    }
}