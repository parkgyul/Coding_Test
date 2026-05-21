import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static int N;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] pi = new int[H];
        int[] pj = new int[H];

        map = new int[N][N];

        int index = 0;
        for(int i =0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j< N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2){
                    pi[index] = i;
                    pj[index++] = j;
                }
            }
        }
        
        int[][] result = new int[N][N];
        StringBuilder sb = new StringBuilder();

        if(M == 0){
            for(int i = 0; i < H; i++){
                result[pi[i]][pj[i]] = -1;
            }
        }else{
            for(int i =0; i < H; i++){
                int r = pi[i];
                int c = pj[i];

                result[r][c] = bfs(r, c);
            }
        }

        for(int i =0 ; i < N; i++){
            for(int j =0 ; j < N; j++){
                sb.append(result[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static int bfs(int r, int c){
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(r, c, 0));
        boolean[][] visited = new boolean[N][N];
        visited[r][c] = true;

        while(!q.isEmpty()){
            Point cur = q.poll();

            for(int i =0; i < 4; i++){
                int ni = cur.i + dx[i];
                int nj = cur.j + dy[i];

                if(ni < 0 || nj < 0 || ni >= N || nj >= N) continue;
                if(visited[ni][nj] || map[ni][nj] == 1) continue;

                if(map[ni][nj] == 3){
                    return cur.cnt+1;
                }

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