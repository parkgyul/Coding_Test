import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] left = new int[N+1];
        int[] middle = new int[N+1];
        int[] right = new int[N+1];

        StringTokenizer st;

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());

            left[i] = Integer.parseInt(st.nextToken());
            middle[i] = Integer.parseInt(st.nextToken());
            right[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N+1][4];

        for(int i = 1; i <= N; i++){
            dp[i][1] = Math.max(dp[i][1], Math.max(dp[i-1][2], dp[i-1][3]) + left[i]);
            dp[i][2] = Math.max(dp[i][2], Math.max(dp[i-1][1], dp[i-1][3]) + middle[i]);
            dp[i][3] = Math.max(dp[i][3], Math.max(dp[i-1][1], dp[i-1][2]) + right[i]);
        }

        System.out.print(Math.max(dp[N][1], Math.max(dp[N][2], dp[N][3])));
    }
}