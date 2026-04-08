import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.io.InputStreamReader;

public class Main{
    static int N, M;
    static int[][] arr;
    static int empty;
    static List<Point> virusList;
    static int[][][] times;
    static int result = Integer.MAX_VALUE;
    static int[] dx = {-1, 1, 0, 0 };
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        virusList = new ArrayList<>();
        arr = new int[N][N];

        for(int i = 0; i < N; i++){
            st =  new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());

                if(arr[i][j] == 0){
                    empty++;
                }else if(arr[i][j] == 2){
                    virusList.add(new Point(i, j));
                }
            }
        }

        if(empty == 0) {
            System.out.print(0);
            return;
        }

        times = new int[N][N][virusList.size()];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                for(int k = 0; k < virusList.size();k++){
                    times[i][j][k] = -1;
                }

            }
        }

        bfs();


        dfs(new int[M], 0, 0);

        System.out.print(result == Integer.MAX_VALUE ? -1 : result);
    }

    public static void dfs(int[] virus, int depth, int index){
        if(depth > M) return;

        if(depth == M){
            int ans = findMin(virus);

            if(ans != -1){
                result = Math.min(result, ans);
            }
            return;
        }

        for(int i = index; i < virusList.size(); i++){
            virus[depth] = i;
            dfs(virus, depth+1, i+1);
        }
    }

    public static int findMin(int[] virus){
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(arr[i][j] != 0) continue;

                int min = Integer.MAX_VALUE;
                for(int v : virus){
                    if(times[i][j][v] != -1){
                        min = Math.min(times[i][j][v], min);
                    }
                }

                if(min == Integer.MAX_VALUE) return -1;
                max = Math.max(max, min);
            }

        }

        return max;
    }

    public static void bfs(){
        Queue<Virus> q = new LinkedList<>();
        for(int i = 0; i < virusList.size(); i++){
            Point p = virusList.get(i);
            q.add(new Virus(i, p.i, p.j, 0));
            times[p.i][p.j][i] = 0;
        }

        while(!q.isEmpty()){
            Virus v = q.poll();

            for(int i = 0; i < 4; i++){
                int ni = v.i + dx[i];
                int nj = v.j + dy[i];

                if(ni < 0 || ni >= N || nj < 0 || nj >= N)
                    continue;

                if(arr[ni][nj] == 1)
                    continue;

                if(times[ni][nj][v.num] != -1)
                    continue;

                times[ni][nj][v.num] = v.cnt+1;
                q.add(new Virus(v.num, ni, nj, v.cnt+1));
            }
        }
    }

    public static class Virus{
        int num, i, j, cnt;
        public Virus(int num, int i, int j, int cnt){
            this.num = num;
            this.i = i;
            this.j = j;
            this.cnt = cnt;
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