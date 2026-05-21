import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx= {-1, 1, 0, 0};
    static int[] dy= {0, 0, -1, 1};
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i =0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0 ; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.print(bfs(0,0));
    }

    static int bfs(int r, int c){
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(r, c, 0));
        visited[r][c] = true;

        while(!q.isEmpty()){
            Point cur = q.poll();

            for(int i = 0; i < 4; i++){
                int ni = cur.i + dx[i];
                int nj = cur.j + dy[i];

                if(ni == N-1 && nj == M-1){
                    return cur.cnt+1;
                }

                if(ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
                if(visited[ni][nj] || map[ni][nj] == 0) continue;

                visited[ni][nj] = true;
                q.add(new Point(ni, nj, cur.cnt+1));
            }
        }

        return -1;
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