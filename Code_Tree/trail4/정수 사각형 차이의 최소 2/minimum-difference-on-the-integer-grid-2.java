import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int N;
    static int[][] map;
    static int[][] dp;
    static int minValue = INF;
    static int maxValue = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                minValue = Math.min(minValue, map[i][j]);
                maxValue = Math.max(maxValue, map[i][j]);
            }
        }

        int answer = INF;

        for (int lowerBound = minValue; lowerBound <= maxValue; lowerBound++) {
            int upperBound = solve(lowerBound);

            if (upperBound == INF) {
                continue;
            }

            answer = Math.min(answer, upperBound - lowerBound);
        }

        System.out.println(answer);
    }

    static int solve(int lowerBound) {
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], INF);
        }

        if (map[0][0] < lowerBound) {
            return INF;
        }

        dp[0][0] = map[0][0];

        for (int i = 1; i < N; i++) {
            if (map[i][0] >= lowerBound && dp[i - 1][0] != INF) {
                dp[i][0] = Math.max(dp[i - 1][0], map[i][0]);
            }
        }

        for (int j = 1; j < N; j++) {
            if (map[0][j] >= lowerBound && dp[0][j - 1] != INF) {
                dp[0][j] = Math.max(dp[0][j - 1], map[0][j]);
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                if (map[i][j] < lowerBound) {
                    continue;
                }

                int bestPrev = Math.min(dp[i - 1][j], dp[i][j - 1]);

                if (bestPrev == INF) {
                    continue;
                }

                dp[i][j] = Math.max(bestPrev, map[i][j]);
            }
        }

        return dp[N - 1][N - 1];
    }
}