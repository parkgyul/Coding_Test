import java.io.*;
import java.util.*;

public class Main {
    static int N, M ;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int max, minK;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        max = 0;
        minK = 1;

        int maxNum = 1;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] > maxNum) maxNum = map[i][j];
            }
        }

        for(int K = 1; K < maxNum; K++){
            visited = new boolean[N][M];
            int cnt = 0;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(visited[i][j] || map[i][j] <= K) continue;
                    visited[i][j] = true;
                    bfs(i, j, K);
                    cnt++;
                }
            }

            if(max < cnt){
                max = cnt;
                minK = K;
            }
        }

        System.out.print(minK + " " + max);
    }
    static void bfs(int si, int ei, int K){
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(si, ei));

        while(!q.isEmpty()){
            Point cur = q.poll();
            
            for(int i = 0; i < 4; i++){
                int ni = cur.i + dx[i];
                int nj = cur.j + dy[i];

                if(ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
                if(visited[ni][nj] || map[ni][nj] <= K) continue;

                visited[ni][nj] = true;
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