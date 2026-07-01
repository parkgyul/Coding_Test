import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        final long NEG = Long.MIN_VALUE / 2;

        // 열 기준: maxLeft[c] = 열 [0..c] 안에서 얻을 수 있는 최대 직사각형 합
        //          maxRight[c] = 열 [c..M-1] 안에서 얻을 수 있는 최대 직사각형 합
        long[] maxLeft = new long[M];
        long[] maxRight = new long[M];
        Arrays.fill(maxLeft, NEG);
        Arrays.fill(maxRight, NEG);

        for (int top = 0; top < N; top++) {
            long[] colSum = new long[M];
            for (int bottom = top; bottom < N; bottom++) {
                for (int j = 0; j < M; j++) {
                    colSum[j] += arr[bottom][j];
                }

                // 왼쪽에서 오른쪽으로 Kadane (prefix 최대 부분합)
                long cur = NEG, best = NEG;
                for (int j = 0; j < M; j++) {
                    cur = Math.max(colSum[j], cur + colSum[j]);
                    best = Math.max(best, cur);
                    maxLeft[j] = Math.max(maxLeft[j], best);
                }

                // 오른쪽에서 왼쪽으로 Kadane (suffix 최대 부분합)
                cur = NEG; best = NEG;
                for (int j = M - 1; j >= 0; j--) {
                    cur = Math.max(colSum[j], cur + colSum[j]);
                    best = Math.max(best, cur);
                    maxRight[j] = Math.max(maxRight[j], best);
                }
            }
        }

        // 행 기준: maxTop[r] = 행 [0..r] 안에서 얻을 수 있는 최대 직사각형 합
        //          maxBottom[r] = 행 [r..N-1] 안에서 얻을 수 있는 최대 직사각형 합
        long[] maxTop = new long[N];
        long[] maxBottom = new long[N];
        Arrays.fill(maxTop, NEG);
        Arrays.fill(maxBottom, NEG);

        for (int left = 0; left < M; left++) {
            long[] rowSum = new long[N];
            for (int right = left; right < M; right++) {
                for (int i = 0; i < N; i++) {
                    rowSum[i] += arr[i][right];
                }

                long cur = NEG, best = NEG;
                for (int i = 0; i < N; i++) {
                    cur = Math.max(rowSum[i], cur + rowSum[i]);
                    best = Math.max(best, cur);
                    maxTop[i] = Math.max(maxTop[i], best);
                }

                cur = NEG; best = NEG;
                for (int i = N - 1; i >= 0; i--) {
                    cur = Math.max(rowSum[i], cur + rowSum[i]);
                    best = Math.max(best, cur);
                    maxBottom[i] = Math.max(maxBottom[i], best);
                }
            }
        }

        long ans = NEG;

        if (M >= 2) {
            for (int c = 0; c < M - 1; c++) {
                if (maxLeft[c] > NEG && maxRight[c + 1] > NEG) {
                    ans = Math.max(ans, maxLeft[c] + maxRight[c + 1]);
                }
            }
        }

        if (N >= 2) {
            for (int r = 0; r < N - 1; r++) {
                if (maxTop[r] > NEG && maxBottom[r + 1] > NEG) {
                    ans = Math.max(ans, maxTop[r] + maxBottom[r + 1]);
                }
            }
        }

        System.out.println(ans);
    }
}