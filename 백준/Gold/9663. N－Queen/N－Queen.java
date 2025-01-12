import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static int total = 0;
    static int[] queens;
    static boolean[] cols;
    static boolean[] d1;
    static boolean[] d2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        queens = new int[n];
        cols = new boolean[n];
        d1 = new boolean[2 * n - 1];
        d2 = new boolean[2 * n - 1];
        dfs(0);
        System.out.println(total);
    }

    public static void dfs(int row) {
        if (row == n) {
            total++;
            return;
        }

        for (int col = 0; col < n; col++) {
            if (cols[col] || d1[row - col + n - 1] || d2[row + col]) continue;

            queens[row] = col;
            cols[col] = true;
            d1[row - col + n - 1] = true;
            d2[row + col] = true;

            dfs(row + 1);

            cols[col] = false;
            d1[row - col + n - 1] = false;
            d2[row + col] = false;
        }
    }
}