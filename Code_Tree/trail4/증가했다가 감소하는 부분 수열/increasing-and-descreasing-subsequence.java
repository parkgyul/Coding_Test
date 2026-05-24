import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dp = new int[n][2];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;

        for (int i = 0; i < n; i++) {
            // 자기 자신 하나만 선택하는 경우
            dp[i][0] = 1; // 증가 상태
            dp[i][1] = 1; // 감소 상태

            for (int j = 0; j < i; j++) {

                // 1. 증가 상태 유지
                // 이전 값 arr[j]보다 현재 값 arr[i]가 크면 증가 수열에 붙일 수 있음
                if (arr[j] < arr[i]) {
                    dp[i][0] = Math.max(dp[i][0], dp[j][0] + 1);
                }

                // 2. 감소 상태 유지
                // 이미 감소 상태인 수열 뒤에 현재 값을 붙이는 경우
                if (arr[j] > arr[i]) {
                    dp[i][1] = Math.max(dp[i][1], dp[j][1] + 1);
                }
            }

            // 3. 증가 상태에서 감소 상태로 전환
            // i번째 원소를 꼭짓점으로 삼는 경우
            dp[i][1] = Math.max(dp[i][1], dp[i][0]);

            answer = Math.max(answer, dp[i][1]);
        }

        System.out.print(answer);
    }
}