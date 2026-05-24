import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 물건 개수
        int M = Integer.parseInt(st.nextToken()); // 최대 무게

        int[] w = new int[N];
        int[] v = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            int weight = w[i - 1];
            int value = v[i - 1];

            for (int j = 0; j <= M; j++) {
                // 1. i번째 물건을 넣지 않는 경우
                dp[i][j] = dp[i - 1][j];

                // 2. i번째 물건을 넣는 경우
                if (j >= weight) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - weight] + value);
                }
            }
        }

        System.out.print(dp[N][M]);
    }
}