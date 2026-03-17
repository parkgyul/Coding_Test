import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    static int N, M;
    static int[][] arr;
    static int max;
    static int cnt;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean[][] visited;
    static int[][] temp;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        temp = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        max = 0;

        dfs(0);

        System.out.print(max);
    }

    public static void dfs(int depth){
        if(depth == 3){
            findMax();
            return;
        }

        for(int a = 0; a <N; a++){
            for(int b = 0; b <M; b++){
                if(arr[a][b] == 0){
                    arr[a][b] = 1;
                    dfs(depth +1);
                    arr[a][b] = 0;
                }
            }
        }
    }

    public static void findMax(){
        boolean[][] checked = new boolean[N][M];
        int[][] copy = new int[arr.length][];
        for(int i = 0; i < arr.length; i++){
            copy[i] = arr[i].clone();
        }

        for(int i = 0; i < N; i ++) {
            for (int j = 0; j < M; j++) {
                if (copy[i][j] == 2 && !checked[i][j]) {
                    bfs(i, j, checked, copy);
                }
            }
        }

        cnt = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(copy[i][j] == 0){
                    cnt++;
                }
            }
        }
        max = Math.max(cnt, max);
    }

    public static void bfs(int i, int j, boolean[][] checked, int[][] copy){
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(i, j));
        checked[i][j] = true;

        while(!q.isEmpty()){
            Point cur = q.poll();
            for(int a = 0; a < 4; a++) {
                int ni = cur.i + dx[a];
                int nj = cur.j + dy[a];

                if (ni < 0 || ni >= N || nj < 0 || nj >= M || checked[ni][nj])
                    continue;

                if(copy[ni][nj] == 0){
                    q.add(new Point(ni, nj));
                    checked[ni][nj] = true;
                    copy[ni][nj] = 2;
                }
            }
        }
    }

    public static class Point{
        int i, j;
        public Point(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
}