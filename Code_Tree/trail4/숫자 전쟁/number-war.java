import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N+1];
        int[] B = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][N + 1];

        for (int i = 1; i <=N; i++) {
            for (int j = 1; j <=N; j++) {

                // 1. 카드 버리기: 둘 다 맨 위 카드 버림
                dp[i][j] = dp[i-1][j-1];

                // 2. 카드 대결
                if (A[i] > B[j]) {
                    // 남우 카드가 더 작음 → 남우 점수 획득, 남우 카드 버림
                    dp[i][j] = Math.max(dp[i][j], B[j] + dp[i][j-1]);
                } else if (A[i] < B[j]) {
                    // 첫 번째 플레이어 카드가 더 작음 → 첫 번째 카드 버림
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                } else {
                    // 같으면 둘 다 버림
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1]);
                }
            }
        }

        System.out.println(dp[N][N]);
    }
}