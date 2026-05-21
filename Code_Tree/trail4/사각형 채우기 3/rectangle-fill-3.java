import java.io.*;

public class Main {
    static final long MOD = 1000000007L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[][] dp = new long[N + 1][4];

        dp[0][0] = 1;

        for (int i = 0; i < N; i++) {
            // 상태 0: 현재 열이 완전히 비어 있음
            dp[i + 1][0] = (dp[i + 1][0] + dp[i][0] * 2) % MOD;
            dp[i + 1][1] = (dp[i + 1][1] + dp[i][0]) % MOD;
            dp[i + 1][2] = (dp[i + 1][2] + dp[i][0]) % MOD;
            dp[i + 1][3] = (dp[i + 1][3] + dp[i][0]) % MOD;

            // 상태 1: 위칸은 이미 채워져 있음
            dp[i + 1][0] = (dp[i + 1][0] + dp[i][1]) % MOD;
            dp[i + 1][2] = (dp[i + 1][2] + dp[i][1]) % MOD;

            // 상태 2: 아래칸은 이미 채워져 있음
            dp[i + 1][0] = (dp[i + 1][0] + dp[i][2]) % MOD;
            dp[i + 1][1] = (dp[i + 1][1] + dp[i][2]) % MOD;

            // 상태 3: 위아래 모두 이미 채워져 있음
            dp[i + 1][0] = (dp[i + 1][0] + dp[i][3]) % MOD;
        }

        System.out.println(dp[N][0]);
    }
}