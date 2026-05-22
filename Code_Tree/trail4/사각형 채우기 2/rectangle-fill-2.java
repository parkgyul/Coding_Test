import java.io.*; 
import java.util.*; 

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int MOD = 10007;

        int[] dp = new int[1001];

        dp[1] = 1;
        dp[2] = 3;

        for(int i = 3; i <= N; i++){
            dp[i] = (dp[i] + dp[i-1] + dp[i-2] *2) % MOD;
        }

        System.out.print(dp[N]);
    }
}