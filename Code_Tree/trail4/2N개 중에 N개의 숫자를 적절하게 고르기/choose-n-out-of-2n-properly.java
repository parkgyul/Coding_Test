import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static int min;
    static int total;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[2 * N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            total += arr[i];
        }

        min = Integer.MAX_VALUE;

        // arr[0]은 무조건 선택한 그룹에 포함한다고 고정
        dfs(1, 1, arr[0]);

        System.out.print(min);
    }

    static void dfs(int selectedCnt, int index, int sum) {
        if (min == 0) {
            return;
        }

        if (selectedCnt == N) {
            min = Math.min(min, Math.abs(total - 2 * sum));
            return;
        }

        int need = N - selectedCnt;
        int remain = 2 * N - index;

        if (remain < need) {
            return;
        }

        for (int i = index; i <= 2 * N - need; i++) {
            dfs(selectedCnt + 1, i + 1, sum + arr[i]);
        }
    }
}