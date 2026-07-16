import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        final int NEG_INF = Integer.MIN_VALUE / 2; // 덧셈 오버플로 방지용
        int[][] dp = new int[N + 1][K + 1];
        for (int[] row : dp) Arrays.fill(row, NEG_INF);

        int answer = Integer.MIN_VALUE;

        for (int i = 1; i <= N; i++) {
            for (int cnt = 0; cnt <= K; cnt++) {
                if (arr[i] < 0) {
                    if (cnt >= 1) {
                        int prev = dp[i - 1][cnt - 1]; // 이전 칸에서 이어붙이는 경우
                        dp[i][cnt] = Math.max(arr[i], prev + arr[i]);
                    }
                    // cnt == 0이면 음수 원소를 포함할 수 없으므로 NEG_INF 그대로 둠
                } else {
                    int prev = dp[i - 1][cnt];
                    dp[i][cnt] = Math.max(arr[i], prev + arr[i]);
                }

                answer = Math.max(answer, dp[i][cnt]);
            }
        }

        System.out.print(answer);
    }
}