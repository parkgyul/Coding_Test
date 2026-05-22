import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[] dx = {1, 0};
    static int[] dy = {0, 1};
    static int minValue = Integer.MAX_VALUE;
    static int maxValue = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                minValue = Math.min(minValue, map[i][j]);
                maxValue = Math.max(maxValue, map[i][j]);
            }
        }

        int left = 0;
        int right = maxValue - minValue;
        int answer = right;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canGo(mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    static boolean canGo(int diff) {
        for (int low = minValue; low + diff <= maxValue; low++) {
            int high = low + diff;

            if (map[0][0] < low || map[0][0] > high) {
                continue;
            }

            if (map[N - 1][N - 1] < low || map[N - 1][N - 1] > high) {
                continue;
            }

            if (isReachable(low, high)) {
                return true;
            }
        }

        return false;
    }

    static boolean isReachable(int low, int high) {
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();

        visited[0][0] = true;
        q.add(new int[]{0, 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            if (r == N - 1 && c == N - 1) {
                return true;
            }

            for (int dir = 0; dir < 2; dir++) {
                int nr = r + dx[dir];
                int nc = c + dy[dir];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                    continue;
                }

                if (visited[nr][nc]) {
                    continue;
                }

                if (map[nr][nc] < low || map[nr][nc] > high) {
                    continue;
                }

                visited[nr][nc] = true;
                q.add(new int[]{nr, nc});
            }
        }

        return false;
    }
}