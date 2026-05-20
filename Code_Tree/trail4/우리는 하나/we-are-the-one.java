import java.io.*;
import java.util.*;

public class Main {
    static int N, K, U, D;
    static int[][] map;
    static boolean[][] visited;
    static int max;
    static List<Point> list;
    static int cnt;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        list = new ArrayList<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                list.add(new Point(i, j));
            }
        }

        max = 0;

        dfs(0,0, new int[K]);

        System.out.print(max);
    }

    static void dfs(int depth, int index, int[] arr){
        if(depth == K){
            cnt = 0;
            visited = new boolean[N][N];
            for(int a : arr){
                Point city = list.get(a);
                bfs(city.i, city.j);
            }
            max = Math.max(cnt, max);
            return;
        }

        for(int i = index; i < list.size(); i++){
            arr[depth] = i;
            dfs(depth+1, i+1, arr);
        }
    }

    static void bfs(int r, int c){
        Queue<Point> q = new LinkedList<>();
        if(!visited[r][c]){
            visited[r][c] = true;
            cnt++;
        }
        q.add(new Point(r,c));

        while(!q.isEmpty()){
            Point cur = q.poll();

            for(int i = 0; i < 4; i++){
                int ni = cur.i + dx[i];
                int nj = cur.j + dy[i];

                if(ni < 0 || ni >= N || nj < 0 || nj >= N) continue;
                if(visited[ni][nj]) continue;
                int gap = Math.abs(map[ni][nj]-map[cur.i][cur.j]);
                if(gap < U || gap > D) continue;

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