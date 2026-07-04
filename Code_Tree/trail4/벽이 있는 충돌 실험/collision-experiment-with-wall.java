import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T, N, M;
    static int[][] map, count, dir;
    static int remain;
    static int[][] delta = {
            {-1, -1},
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };

    static boolean isValid(int row, int col) {
        return (row>=0&&col>=0&&row<N&&col<N);
    }

    static void go() {
        int time = 2 * N;

        while (time-- > 0) {
            for (int r=0;r<N;r++) {
                for (int c=0;c<N;c++) {
                    if (map[r][c] == 0) continue;

                    int direction = map[r][c];
                    int nextRow = r + delta[direction][0];
                    int nextCol = c + delta[direction][1];

                    if (!isValid(nextRow, nextCol)) {
                        switch (direction) {
                            case 1: direction = 3; break;
                            case 2: direction = 4; break;
                            case 3: direction = 1; break;
                            case 4: direction = 2; break;
                        }
                        nextRow = r;
                        nextCol = c;
                    }

                    count[nextRow][nextCol] += 1;
                    dir[nextRow][nextCol] = direction;
                }
            }

            for (int r=0;r<N;r++) {
                for (int c=0;c<N;c++) {
                    int num = count[r][c];
                    int direction = dir[r][c];

                    if (num > 1) {
                        remain -= num;
                        direction = 0;
                    }

                    map[r][c] = direction;
                    count[r][c] = 0;
                    dir[r][c] = 0;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t=0;t<T;t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            remain = M;
            map = new int[N][N];
            count = new int[N][N];
            dir = new int[N][N];

            for (int i=0;i<M;i++) {
                st = new StringTokenizer(br.readLine());
                int row = Integer.parseInt(st.nextToken()) - 1;
                int col = Integer.parseInt(st.nextToken()) - 1;
                String direction = st.nextToken();

                switch (direction) {
                    case "U": map[row][col] = 1;break;
                    case "D": map[row][col] = 3;break;
                    case "R": map[row][col] = 2;break;
                    case "L": map[row][col] = 4;break;
                }
            }

            go();

            sb.append(remain).append("\n");
        }
        System.out.print(sb.toString().trim());
    }
}

