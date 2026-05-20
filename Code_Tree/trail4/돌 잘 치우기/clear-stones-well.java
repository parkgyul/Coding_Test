import java.io.*;
import java.util.*;

public class Main {
    static int N, K, M;
    static int[] si;
    static int[] sj;
    static int[][] map;
    static boolean[][] visited;
    static List<Point> stones;
    static int max;
    static int cnt;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        stones = new ArrayList<>();

        map = new int[N][N];
        si = new int[K];
        sj = new int[K];

        for(int i =0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){
                    stones.add(new Point(i, j));
                }
            }
        }

        for(int i =0 ; i < K; i++){
            st = new StringTokenizer(br.readLine());
            si[i] = Integer.parseInt(st.nextToken())-1;
            sj[i] = Integer.parseInt(st.nextToken())-1;
        }

        max = Integer.MIN_VALUE;

        dfs(0, 0);

        System.out.print(max);
    }

    static void dfs(int depth, int index){
        if(depth == M){
            visited = new boolean[N][N];
            cnt = 0;
            for(int i =0 ; i < K; i++){
                int r = si[i];
                int c = sj[i];

                bfs(r, c);
            }

            max = Math.max(cnt, max);

            return;
        }

        for(int i = index; i< stones.size(); i++){
            Point cur = stones.get(i);

            map[cur.i][cur.j] = 0;
            dfs(depth+1, i+1);
            map[cur.i][cur.j] = 1;
        }

    }

    static void bfs(int r, int c){
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(r, c));

        if(!visited[r][c]){
            visited[r][c] = true;
            cnt ++;
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