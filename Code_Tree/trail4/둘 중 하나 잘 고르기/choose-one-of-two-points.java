import java.io.*; 
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
    
        int[] R = new int[2*N+1];
        int[] B = new int[2*N+1];

        int[][] dp = new int[2*N+1][2*N+1];

        for(int i = 1; i <= 2*N; i++){
            st = new StringTokenizer(br.readLine());
            R[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i <= 2*N; i++){
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }

        dp[0][0] = 0;

        for(int i = 1; i <= 2*N; i++){
            for(int j = 0; j <= Math.min(i, N); j++){
                if(dp[i-1][j] != Integer.MIN_VALUE)
                    dp[i][j] = Math.max(dp[i-1][j] + B[i], dp[i][j]);

                if(j > 0 && dp[i-1][j-1] != Integer.MIN_VALUE)
                    dp[i][j] = Math.max(dp[i-1][j-1] + R[i], dp[i][j]);
            }
        }
    
        System.out.print(dp[2*N][N]);
    }
}