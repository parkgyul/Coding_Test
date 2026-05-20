import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, 1, -1, 0};
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited[0][0] = true;

        if(bfs()) System.out.println(1);
        else System.out.println(0);
    }

    static boolean bfs(){
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));
        
        while(!q.isEmpty()){
            Point cur = q.poll();
            
            for(int i =0 ; i < 4; i++){
                int ni = cur.i + dx[i];
                int nj = cur.j + dy[i];
                if(ni == N-1 && nj == M-1){
                    return true;
                }

                if(ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
                if(visited[ni][nj] || map[ni][nj] == 0) continue;

                visited[ni][nj] = true;
                q.add(new Point(ni, nj));
            }
        }

        return false;
    }

    static class Point{
        int i, j;
        Point(int i, int j){
            this.i = i;
            this.j = j;
        }
     }
}