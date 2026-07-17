import java.io.*;
import java.util.*;

public class Main {
    static final long OFFSET = (long)Math.pow(10, 9) + 7;
    public static void main(String[] args)throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        long[][][] dp = new long[N+1][3][3];

        dp[0][0][0] = 1;

        long answer = 0;

        for(int i = 1; i <= N; i++){
            for(int j = 0; j < 3; j++){
                for(int k = 0; k < 3; k++){

                    // G 선택
                    dp[i][j][0] =  (dp[i][j][0] + dp[i-1][j][k]) % OFFSET;

                    if(k < 2){
                        dp[i][j][k+1] = (dp[i][j][k+1] + dp[i-1][j][k]) % OFFSET;
                    }

                    if(j < 2){
                        dp[i][j+1][0] = (dp[i][j+1][0] + dp[i-1][j][k]) % OFFSET;
                    }
                    
                }
            }
        }

        for(int j = 0; j < 3; j++){
            for(int k = 0; k < 3; k++){
                answer += (dp[N][j][k]%OFFSET);
            }
        }

         System.out.print(answer % OFFSET);
    }
}