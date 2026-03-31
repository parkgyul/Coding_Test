import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] arr;
    static long[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new long[N + 1][N + 1][3];

        // 시작 상태 (가로)
        dp[1][2][0] = 1;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {

                if (arr[i][j] == 1) continue;

                // 가로
                if (j - 1 >= 1) {
                    dp[i][j][0] += dp[i][j - 1][0];
                    dp[i][j][0] += dp[i][j - 1][2];
                }

                // 세로
                if (i - 1 >= 1) {
                    dp[i][j][1] += dp[i - 1][j][1];
                    dp[i][j][1] += dp[i - 1][j][2];
                }

                // 대각선
                if (i - 1 >= 1 && j - 1 >= 1) {
                    if (arr[i - 1][j] == 0 && arr[i][j - 1] == 0) {
                        dp[i][j][2] += dp[i - 1][j - 1][0];
                        dp[i][j][2] += dp[i - 1][j - 1][1];
                        dp[i][j][2] += dp[i - 1][j - 1][2];
                    }
                }
            }
        }

        long result = dp[N][N][0] + dp[N][N][1] + dp[N][N][2];
        System.out.println(result);
    }
}