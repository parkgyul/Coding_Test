import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int INF = Integer.MAX_VALUE / 2;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] money = new int[N];
        int[] dp = new int[M + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            money[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int i = 1; i <= M; i++) {
            for (int j = 0; j < N; j++) {
                if (i - money[j] >= 0 && dp[i - money[j]] != INF) {
                    dp[i] = Math.min(dp[i], dp[i - money[j]] + 1);
                }
            }
        }

        System.out.print(dp[M] == INF ? -1 : dp[M]);
    }
}