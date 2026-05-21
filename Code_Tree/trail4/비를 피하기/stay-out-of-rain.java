import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static int[][] result;
    static int N, M;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] pi = new int[M];
        int[] pj = new int[M];

        map = new int[N][N];
        result = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(result[i], Integer.MAX_VALUE);
        }

        int index = 0;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 3){
                    pi[index] = i;
                    pj[index++] = j;
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        if(M != 0){
            bfs(pi, pj);
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] == 2){
                    sb.append(result[i][j] == Integer.MAX_VALUE ? -1 : result[i][j]).append(" ");
                } else {
                    sb.append(0).append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static void bfs(int[] pi, int[] pj){
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        for(int i = 0; i < M; i++){
            int r = pi[i];
            int c = pj[i];

            q.add(new Point(r, c, 0));
            visited[r][c] = true;
            result[r][c] = 0;
        }

        while(!q.isEmpty()){
            Point cur = q.poll();

            for(int i = 0; i < 4; i++){
                int ni = cur.i + dx[i];
                int nj = cur.j + dy[i];

                if(ni < 0 || nj < 0 || ni >= N || nj >= N) continue;
                if(visited[ni][nj] || map[ni][nj] == 1) continue;

                visited[ni][nj] = true;
                result[ni][nj] = cur.cnt + 1;
                q.add(new Point(ni, nj, cur.cnt + 1));
            }
        }
    }

    static class Point{
        int i, j, cnt;

        Point(int i, int j, int cnt){
            this.i = i;
            this.j = j;
            this.cnt = cnt;
        }
    }
}