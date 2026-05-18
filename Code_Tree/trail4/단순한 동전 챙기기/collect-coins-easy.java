import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static boolean[] nums;
    static Map<Integer, Point> map;

    static int min;
    static int ei, ej;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        nums = new boolean[10];
        map = new HashMap<>();

        int si = -1;
        int sj = -1;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();

            for (int j = 0; j < N; j++) {
                char ch = str.charAt(j);

                if (ch == 'S') {
                    si = i;
                    sj = j;
                } else if (ch == 'E') {
                    ei = i;
                    ej = j;
                } else if ('1' <= ch && ch <= '9') {
                    int num = ch - '0';
                    nums[num] = true;
                    map.put(num, new Point(i, j));
                }
            }
        }

        if (map.size() < 3) {
            System.out.println(-1);
            return;
        }

        min = Integer.MAX_VALUE;

        dfs(1, 0, 0, si, sj);

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    static void dfs(int index, int coinCnt, int cnt, int prevI, int prevJ) {
        if (cnt + dist(prevI, prevJ, ei, ej) >= min) {
            return;
        }

        if (coinCnt == 3) {
            min = Math.min(min, cnt + dist(prevI, prevJ, ei, ej));
            return;
        }

        for (int num = index; num <= 9; num++) {
            if (!nums[num]) continue;

            Point next = map.get(num);

            dfs(
                num + 1,
                coinCnt + 1,
                cnt + dist(prevI, prevJ, next.i, next.j),
                next.i,
                next.j
            );
        }
    }

    static int dist(int i1, int j1, int i2, int j2) {
        return Math.abs(i1 - i2) + Math.abs(j1 - j2);
    }

    static class Point {
        int i;
        int j;

        Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}