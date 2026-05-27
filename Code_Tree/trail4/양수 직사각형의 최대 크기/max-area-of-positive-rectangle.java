import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = -1;

        for (int top = 0; top < N; top++) {
            boolean[] possible = new boolean[M];
            Arrays.fill(possible, true);

            for (int bottom = top; bottom < N; bottom++) {

                for (int col = 0; col < M; col++) {
                    if (arr[bottom][col] <= 0) {
                        possible[col] = false;
                    }
                }

                int height = bottom - top + 1;
                int width = 0;

                for (int col = 0; col < M; col++) {
                    if (possible[col]) {
                        width++;
                        answer = Math.max(answer, height * width);
                    } else {
                        width = 0;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}