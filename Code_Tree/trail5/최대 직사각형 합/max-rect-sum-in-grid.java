import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N+1][N+1];

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] prefix = new int[N+1][N+1];
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                prefix[i][j] = prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1] + arr[i][j];
            }
        }

        int max = Integer.MIN_VALUE;

        int[] dp = new int[N+1];

        for(int x1 = 1; x1 <= N; x1++){
            for(int x2 = x1; x2 <= N; x2++){
                dp[0] = 0;
                for(int y = 1; y <= N; y++){
                    int value = prefix[x2][y] - prefix[x1-1][y] - prefix[x2][y-1] + prefix[x1-1][y-1];
                    dp[y] = Math.max(value, dp[y-1] + value);
                    max = Math.max(max, dp[y]);
                }
            }
        }

        System.out.print(max);
        
    }
}