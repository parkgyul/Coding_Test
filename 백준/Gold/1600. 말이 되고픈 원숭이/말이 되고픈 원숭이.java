import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] bi = {-1, 0, 0, 1};
    static int[] bj = {0, -1, 1, 0};
    static int[] di = {-1, -1, -2, -2, 2, 2, 1, 1};
    static int[] dj = {-2, 2, -1, 1, -1, 1, -2, 2};
    static int[][] ary;
    static boolean[][][] visited;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        
        
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        visited = new boolean[k + 1][n][m];
        ary = new int[n][m];


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                ary[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = bfs(k, n, m);
        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);

        }
    }

    public static int bfs(int k, int n, int m) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0, 0, 0));
        visited[0][0][0] = true;

        int result = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            Point point = q.poll();
            int i = point.i;
            int j = point.j;
            int nowK = point.k;
            int answer = point.answer;



            if (i == n - 1 && j == m - 1) {
                result = answer;
                return result;
            }

            for (int a = 0; a < 4; a++) {
                int moveI = i + bi[a];
                int moveJ = j + bj[a];

                if (moveI < 0 || moveI >= n || moveJ < 0 || moveJ >= m || ary[moveI][moveJ] == 1 || visited[nowK][moveI][moveJ]) {
                    continue;
                }

                visited[nowK][moveI][moveJ] = true;
                q.add(new Point(moveI, moveJ, nowK, answer + 1));
            }

            if (nowK == k) {
                continue;
            }


            for (int a = 0; a < 8; a++) {
                int moveI = i + di[a];
                int moveJ = j + dj[a];
                if (moveI < 0 || moveI >= n || moveJ < 0 || moveJ >= m || ary[moveI][moveJ] == 1 || visited[nowK+1][moveI][moveJ]) {
                    continue;
                }

                visited[nowK + 1][moveI][moveJ] = true;
                q.add(new Point(moveI, moveJ, nowK + 1, answer + 1));
            }
        }
        return result;
    }

    public static class Point {
        int i;
        int j;
        int k;
        int answer;

        public Point(int i, int j, int k, int answer) {
            this.i = i;
            this.j = j;
            this.k = k;
            this.answer = answer;
        }
    }
}