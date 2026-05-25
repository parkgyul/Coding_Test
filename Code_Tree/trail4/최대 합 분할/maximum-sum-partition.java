import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];

        int sum = 0;

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }

        int[] dp = new int[sum + 1];
        Arrays.fill(dp, -1);

        dp[0] = 0;

        for (int x : arr) {
            int[] next = dp.clone();

            for (int diff = 0; diff <= sum; diff++) {
                if (dp[diff] == -1) continue;

                int small = dp[diff];

                // 1. x를 큰 쪽에 넣는 경우
                if (diff + x <= sum) {
                    next[diff + x] = Math.max(next[diff + x], small);
                }

                // 2. x를 작은 쪽에 넣는 경우
                int newDiff = Math.abs(diff - x);
                int newSmall = small + Math.min(diff, x);

                next[newDiff] = Math.max(next[newDiff], newSmall);

                // 3. x를 C에 넣는 경우는 next = dp.clone()으로 이미 처리됨
            }

            dp = next;
        }

        System.out.println(dp[0]);
    }
}