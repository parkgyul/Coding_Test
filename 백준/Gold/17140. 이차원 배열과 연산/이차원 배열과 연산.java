import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] arr = new int[101][101];
    static int rSize = 3, cSize = 3;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        while (time <= 100) {
            if (arr[r][c] == k) {
                System.out.println(time);
                return;
            }

            if (rSize >= cSize) {
                operateR();
            } else {
                operateC();
            }
            time++;
        }

        System.out.println(-1);
    }

    static void operateR() {
        int[][] next = new int[101][101];
        int nextCSize = 0;

        for (int i = 1; i <= rSize; i++) {
            List<Integer> line = makeSortedLineFromRow(i);

            nextCSize = Math.max(nextCSize, line.size());

            for (int j = 0; j < line.size() && j < 100; j++) {
                next[i][j + 1] = line.get(j);
            }
        }

        arr = next;
        cSize = Math.min(nextCSize, 100);
    }

    static void operateC() {
        int[][] next = new int[101][101];
        int nextRSize = 0;

        for (int j = 1; j <= cSize; j++) {
            List<Integer> line = makeSortedLineFromCol(j);

            nextRSize = Math.max(nextRSize, line.size());

            for (int i = 0; i < line.size() && i < 100; i++) {
                next[i + 1][j] = line.get(i);
            }
        }

        arr = next;
        rSize = Math.min(nextRSize, 100);
    }

    static List<Integer> makeSortedLineFromRow(int row) {
        int[] count = new int[101];

        for (int j = 1; j <= cSize; j++) {
            int num = arr[row][j];
            if (num != 0) count[num]++;
        }

        return sortAndFlatten(count);
    }

    static List<Integer> makeSortedLineFromCol(int col) {
        int[] count = new int[101];

        for (int i = 1; i <= rSize; i++) {
            int num = arr[i][col];
            if (num != 0) count[num]++;
        }

        return sortAndFlatten(count);
    }

    static List<Integer> sortAndFlatten(int[] count) {
        List<int[]> pairs = new ArrayList<>();

        for (int num = 1; num <= 100; num++) {
            if (count[num] > 0) {
                pairs.add(new int[]{num, count[num]});
            }
        }

        pairs.sort((a, b) -> {
            if (a[1] != b[1]) return a[1] - b[1];
            return a[0] - b[0];
        });

        List<Integer> result = new ArrayList<>();
        for (int[] pair : pairs) {
            result.add(pair[0]);
            result.add(pair[1]);
        }

        return result;
    }
}