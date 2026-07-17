import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] dp = new int[N+1][10];
        Arrays.fill(dp[1], 1);
        dp[1][0] = 0;

        final int OFFSET = (int)Math.pow(10, 9) + 7;

        for(int i = 2; i <= N; i++){
            for(int j = 0; j <= 9; j ++){
                if(j-1 >= 0){
                    dp[i][j-1] = (dp[i][j-1] + dp[i-1][j]) % OFFSET ;
                }

                if(j+1 <= 9){
                    dp[i][j+1] = (dp[i][j+1] + dp[i-1][j]) % OFFSET;
                }
            }
        }

        int answer = 0;
        
        for(int i = 0; i < 10; i++){
            answer = (answer + dp[N][i]) % OFFSET;
        }

        System.out.print(answer % OFFSET);
    }
}