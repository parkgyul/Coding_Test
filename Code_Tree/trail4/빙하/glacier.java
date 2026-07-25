import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        int rest = 0;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 1) rest++;
            }
        }
        int time = 0;
        visited = new boolean[N][M];
        visited[0][0] = true;
        Queue<Point> melted = new LinkedList<>();
        melted.add(new Point(0, 0));

        Queue<Point> melting = bfs(melted);
        int lastSize = melting.size();

        while(!melting.isEmpty()){
            time ++ ;

            melting = bfs(melting);
            if(!melting.isEmpty()) lastSize = melting.size();
        }

        System.out.print(time + " " + lastSize);

    }

    static Queue<Point> bfs(Queue<Point> q){
        Queue<Point> newMelting = new LinkedList<>();

        while(!q.isEmpty()){
            Point cur = q.poll();

            for(int i =0 ; i < 4; i++){
                int ni = cur.i + dx[i];
                int nj = cur.j + dy[i];

                if(ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
                if(visited[ni][nj]) continue;

                visited[ni][nj] = true;
                if(map[ni][nj] == 0) q.add(new Point(ni, nj));
                else newMelting.add(new Point(ni, nj));
            }
        }
        return newMelting;
    }

    static class Point{
        int i, j;

        Point(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
}