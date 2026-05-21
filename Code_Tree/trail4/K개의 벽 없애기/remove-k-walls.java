import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[][] map;
    static int[][] visited;
    static int si, sj, ei, ej;
    static int min;
    static List<int[]> walls;
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, 1, -1, 0};
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        walls = new ArrayList<>();

        for(int i =0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){
                    walls.add(new int[]{i, j});
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        si = Integer.parseInt(st.nextToken())-1;
        sj = Integer.parseInt(st.nextToken())-1;
        st = new StringTokenizer(br.readLine());
        ei = Integer.parseInt(st.nextToken())-1;
        ej = Integer.parseInt(st.nextToken())-1;

        min = Integer.MAX_VALUE;
        dfs(0, 0);

        System.out.print(min == Integer.MAX_VALUE ? -1 : min);
    }

    static int bfs(){
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(si, sj, 0));
        boolean[][] visited = new boolean[N][N];
        visited[si][sj] = true;

        while(!q.isEmpty()){
            Point cur = q.poll();

            for(int i =0 ; i < 4; i++){
                int ni = cur.i + dx[i];
                int nj = cur.j + dy[i];

                if(ni == ei && nj == ej){
                    return cur.cnt + 1;
                }

                if(ni < 0 || nj < 0 || ni >= N || nj >= N) continue;
                if(visited[ni][nj] || map[ni][nj] == 1) continue;

                visited[ni][nj] = true;
                q.add(new Point(ni, nj, cur.cnt+1));
            }
        }

        return Integer.MAX_VALUE;
    }

    static void dfs(int depth, int index){
        if(depth == K){
            min = Math.min(bfs(), min);
            return;
        }


        for(int i = index; i < walls.size(); i++){
            int[] wall = walls.get(i);

            map[wall[0]][wall[1]] = 0;
            dfs(depth+1, i+1);
            map[wall[0]][wall[1]] = 1;
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