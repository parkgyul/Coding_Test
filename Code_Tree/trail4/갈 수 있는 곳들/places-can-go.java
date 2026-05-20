import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int cnt;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visited = new boolean[N][N];

        for(int i =0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0 ; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            bfs(r-1, c-1);
        }

        System.out.print(cnt);
    }

    static void bfs(int r, int c){
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(r, c));

        if(!visited[r][c]){
            visited[r][c] = true;
            cnt++;
        }

        while(!q.isEmpty()){
            Point cur = q.poll();

            for(int i =0 ; i < 4; i++){
                int ni = cur.i + dx[i];
                int nj = cur.j + dy[i];

                if(ni < 0 || ni >= N || nj < 0 || nj >= N) continue;
                if(visited[ni][nj] || map[ni][nj] == 1) continue;

                visited[ni][nj] = true;
                cnt++;
                q.add(new Point(ni, nj));
            }
        }
    }

    static class Point{
        int i, j;
        Point(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
}