import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] arr;
    static int countMinus;
    static int countZero;
    static int countPlus;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        countMinus = 0;
        countZero = 0;
        countPlus = 0;

        divideConquer(0, 0, N);

        System.out.println(countMinus);
        System.out.println(countZero);
        System.out.println(countPlus);
    }

    public static void divideConquer(int row, int col, int size) {
        if (isSame(row, col, size)) {
            if (arr[row][col] == -1) countMinus++;
            else if (arr[row][col] == 0) countZero++;
            else if (arr[row][col] == 1) countPlus++;
            return;
        }

        // 배열을 9개의 같은 크기로 나누기
        int newSize = size / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                divideConquer(row + i * newSize, col + j * newSize, newSize);
            }
        }
    }

    public static boolean isSame(int row, int col, int size) {
        int firstElement = arr[row][col];
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (arr[i][j] != firstElement) {
                    return false;
                }
            }
        }
        return true;
    }
}