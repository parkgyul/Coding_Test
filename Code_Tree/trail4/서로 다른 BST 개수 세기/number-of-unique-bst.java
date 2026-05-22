import java.io.*; 
import java.util.*; 

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] dp = new long[20];

        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= N; i++){
            for(int left = 0; left <= i-1; left++){
                int right = i-1-left;
                dp[i] += (dp[left] * dp[right]);
            }
        }

        System.out.print(dp[N]);
    }
}