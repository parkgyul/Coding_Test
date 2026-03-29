import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.LinkedList;

public class Main{
    // 17:47
    static int N, M;
    static int[][] arr;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for(int i = 0; i < N; i++){
            String str = br.readLine();
            for(int j = 0; j < M; j++){
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        System.out.print(bfs());

    }

    public static int bfs(){
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0,0, 1, 0));
        boolean[][][] visited = new boolean[N][M][2];
        visited[0][0][0] = true;

        while(!q.isEmpty()){
            Point cur = q.poll();

            if(cur.i == N-1 && cur.j == M-1){
                return cur.cnt;
            }

            for(int i = 0; i < 4; i++){
                int next_i = cur.i + dx[i];
                int next_j = cur.j + dy[i];

                if(next_i < 0 || next_j < 0 || next_i >= N || next_j >= M )
                    continue;

                if(arr[next_i][next_j] == 0 && !visited[next_i][next_j][cur.broken]){
                    visited[next_i][next_j][cur.broken] = true;
                    q.add(new Point(next_i, next_j, cur.cnt+1, cur.broken));
                }

                if(arr[next_i][next_j] == 1 && cur.broken == 0 && !visited[next_i][next_j][1]){
                    visited[next_i][next_j][1] = true;
                    q.add(new Point(next_i, next_j, cur.cnt+1, 1));
                }
            }
        }

        return -1;
    }

    public static class Point{
        int i, j, cnt, broken;
        public Point(int i, int j, int cnt, int broken){
            this.i = i;
            this.j = j;
            this.cnt = cnt;
            this.broken = broken;
        }
    }
}