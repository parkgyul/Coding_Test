import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][][] dp = new int[N+1][2][K+1];

        for(int i = 0; i <= N; i++){
            for(int j = 0; j <= K; j++){
                dp[i][0][j] = -1;
                dp[i][1][j] = -1;
            }
        }

        String str = br.readLine();

        char ch = str.charAt(0);
        if(ch == 'L'){
            dp[1][0][0] = 1; // 옮기지 않고 받음
            dp[1][1][1] = 0; // 옮기고 못받음
        }else{
            dp[1][1][1] = 1; // 옮기고 받음
            dp[1][0][0] = 0; // 옮기지 않고 못받음
        }


        for(int i = 2; i <= N; i++){
            ch = str.charAt(i-1);
            for(int j = 0; j <= K; j++){
                if(j != 0){
                    dp[i][0][j] = Math.max(dp[i-1][1][j-1] + (ch == 'L' ? 1 : 0), dp[i][0][j]);
                    dp[i][1][j] = Math.max(dp[i-1][0][j-1] + (ch == 'R' ? 1 : 0), dp[i][1][j]);
                }

                dp[i][0][j] = Math.max(dp[i-1][0][j] + (ch == 'L' ? 1 : 0) , dp[i][0][j]);
                dp[i][1][j] = Math.max(dp[i-1][1][j] + (ch == 'R' ? 1 : 0) , dp[i][1][j]);
            }
        }

        int max = 0;

        for(int i = 0; i <= K; i++){
            max = Math.max(max, Math.max(dp[N][0][i], dp[N][1][i]));
        }

        System.out.print(max);
    }
}