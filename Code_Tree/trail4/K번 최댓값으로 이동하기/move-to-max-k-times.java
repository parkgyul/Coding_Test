import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for(int i =0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken())-1;
        int c = Integer.parseInt(st.nextToken())-1;

        while(K-- > 0){
            visited = new boolean[N][N];
            Point move = bfs(r, c);
            if(move.i == N){
                break;
            }

            r = move.i;
            c = move.j;
        }

        System.out.print((r+1) + " " + (c+1));
    }

    static Point bfs(int r, int c){
        int num = map[r][c];
        int max = Integer.MIN_VALUE;
        int maxI = N;
        int maxJ = N;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(r, c));
        visited[r][c] = true;

        while(!q.isEmpty()){
            Point cur = q.poll();

            for(int i = 0; i < 4; i++){
                int ni = cur.i + dx[i];
                int nj = cur.j + dy[i];

                if(ni < 0 || ni >= N || nj < 0 || nj >= N) continue;
                if(visited[ni][nj] || map[ni][nj] >= num) continue;

                visited[ni][nj] = true;
                if(map[ni][nj] > max){
                    max = map[ni][nj];
                    maxI = ni;
                    maxJ = nj;
                }else if(map[ni][nj] == max){
                    if(maxI > ni){
                        maxI = ni;
                        maxJ = nj;
                    }else if(maxI == ni){
                        if(maxJ > nj) maxJ = nj;
                    }
                }
                q.add(new Point(ni, nj));
            }
        }

        return new Point(maxI, maxJ);
    }

    static class Point{
        int i, j;
        Point(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
}