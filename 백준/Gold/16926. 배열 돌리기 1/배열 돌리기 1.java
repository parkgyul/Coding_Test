import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int layers = Math.min(n, m) / 2;

        for (int layer = 0; layer < layers; layer++) {

            int top = layer;
            int left = layer;
            int bottom = n - 1 - layer;
            int right = m - 1 - layer;

            int perimeter = 2 * (bottom - top + right - left);
            int rotate = r % perimeter;

            for (int k = 0; k < rotate; k++) {

                int temp = arr[top][left];

                // 위쪽 
                for (int i = left; i < right; i++)
                    arr[top][i] = arr[top][i + 1];

                // 오른쪽 
                for (int i = top; i < bottom; i++)
                    arr[i][right] = arr[i + 1][right];

                // 아래쪽 
                for (int i = right; i > left; i--)
                    arr[bottom][i] = arr[bottom][i - 1];

                // 왼쪽 
                for (int i = bottom; i > top; i--)
                    arr[i][left] = arr[i - 1][left];

                arr[top + 1][left] = temp;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}