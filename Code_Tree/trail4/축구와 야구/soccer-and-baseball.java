import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        long[][][] dp = new long[N+1][12][10];

        int[] soccer = new int[N+1];
        int[] baseball = new int[N+1];

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            soccer[i] = Integer.parseInt(st.nextToken());
            baseball[i] = Integer.parseInt(st.nextToken());
        }

        for(int i =1; i <= N; i++){
            for(int j = 0; j <= 11; j++){
                for(int k = 0; k <= 9; k++){
                    // 선택하지 않음.
                    dp[i][j][k] = Math.max(dp[i][j][k], dp[i-1][j][k]);

                    // 축구 선택
                    if(j-1 >= 0){
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i-1][j-1][k] + soccer[i]);
                    }

                    // 야구 선택
                    if(k-1 >= 0 ){
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i-1][j][k-1] + baseball[i]);
                    }
                }
            }
        }

        System.out.print(dp[N][11][9]);
    }
}