import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] num = new int[]{1,2,5};
        int[] dp = new int[N+1];

        Arrays.fill(dp, 0);
        dp[0] = 1;

        for(int i = 0; i <= N; i++){
            for(int j = 0; j < 3; j++){
                if(i- num[j] >= 0){
                    dp[i] = (dp[i] + dp[i-num[j]]) % 10007;
                }
            }
        }

        System.out.print(dp[N]);
    }
}