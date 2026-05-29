import java.io.*;
import java.util.*;

public class Main {
    static int N, M, Q;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());

            int row = Integer.parseInt(st.nextToken()) - 1;
            char dir = st.nextToken().charAt(0);

            // 1. 현재 행 회전
            rotate(row, dir);

            // 2. 위쪽 전파
            spread(row, -1, opposite(dir));

            // 3. 아래쪽 전파
            spread(row, 1, opposite(dir));
        }

        print();
    }

    // 전파 처리
    static void spread(int startRow, int delta, char dir) {
        int prev = startRow;
        int cur = startRow + delta;

        while (cur >= 0 && cur < N) {
            // 이전 행과 현재 행에 같은 열의 같은 값이 없으면 전파 종료
            if (!hasSameColumnValue(prev, cur)) {
                break;
            }

            // 전파되면 현재 행을 회전
            rotate(cur, dir);

            // 다음 행으로 이동
            prev = cur;
            cur += delta;

            // 방향 반전
            dir = opposite(dir);
        }
    }

    // 같은 열에 같은 값이 하나라도 있는지 확인
    static boolean hasSameColumnValue(int r1, int r2) {
        for (int j = 0; j < M; j++) {
            if (arr[r1][j] == arr[r2][j]) {
                return true;
            }
        }
        return false;
    }

    // L: 왼쪽에서 바람 → 오른쪽으로 이동
    // R: 오른쪽에서 바람 → 왼쪽으로 이동
    static void rotate(int row, char dir) {
        if (dir == 'L') {
            rotateRight(row);
        } else {
            rotateLeft(row);
        }
    }

    static void rotateRight(int row) {
        int temp = arr[row][M - 1];

        for (int j = M - 1; j >= 1; j--) {
            arr[row][j] = arr[row][j - 1];
        }

        arr[row][0] = temp;
    }

    static void rotateLeft(int row) {
        int temp = arr[row][0];

        for (int j = 0; j < M - 1; j++) {
            arr[row][j] = arr[row][j + 1];
        }

        arr[row][M - 1] = temp;
    }

    static char opposite(char dir) {
        return dir == 'L' ? 'R' : 'L';
    }

    static void print() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }
}