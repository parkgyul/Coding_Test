import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static boolean flag;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        dfs(0);

        System.out.println(sb);
    }

    public static void dfs(int depth) {
        if (flag) return;

        if (!isPossible(depth)) {
            return;
        }

        if (depth == N) {
            for (int i = 0; i < N; i++) {
                sb.append(arr[i]);
            }
            flag = true;
            return;
        }

        for (int num = 4; num <= 6; num++) {
            arr[depth] = num;
            dfs(depth + 1);

            if (flag) return;
        }
    }

    public static boolean isPossible(int depth) {
        for (int size = 1; size <= depth / 2; size++) {
            boolean same = true;

            for (int i = 0; i < size; i++) {
                if (arr[depth - 1 - i] != arr[depth - 1 - size - i]) {
                    same = false;
                    break;
                }
            }

            if (same) {
                return false;
            }
        }

        return true;
    }
}