import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[][] dp;
    static int max = 1;

    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, dfs(i, j));
            }
        }

        System.out.println(max);
    }

    static int dfs(int r, int c) {
        if (dp[r][c] != 0) {
            return dp[r][c];
        }

        dp[r][c] = 1;

        for (int d = 0; d < 4; d++) {
            int nr = r + dx[d];
            int nc = c + dy[d];

            if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                continue;
            }

            if (map[nr][nc] <= map[r][c]) {
                continue;
            }

            dp[r][c] = Math.max(dp[r][c], dfs(nr, nc) + 1);
        }

        return dp[r][c];
    }
}