import java.io.*;
import java.util.*;

public class Main {
    static final int NEG = -1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] coin = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            coin[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][4];

        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], NEG);
        }

        dp[0][0] = 0;

        for (int i = 1; i <= N; i++) {
            for (int cnt = 0; cnt <= 3; cnt++) {

                // 1. i-1층에서 1계단 올라오는 경우
                if (i - 1 >= 0 && cnt - 1 >= 0 && dp[i - 1][cnt - 1] != NEG) {
                    dp[i][cnt] = Math.max(dp[i][cnt], dp[i - 1][cnt - 1] + coin[i]);
                }

                // 2. i-2층에서 2계단 올라오는 경우
                if (i - 2 >= 0 && dp[i - 2][cnt] != NEG) {
                    dp[i][cnt] = Math.max(dp[i][cnt], dp[i - 2][cnt] + coin[i]);
                }
            }
        }

        int answer = 0;

        for (int cnt = 0; cnt <= 3; cnt++) {
            answer = Math.max(answer, dp[N][cnt]);
        }

        System.out.println(answer);
    }
}