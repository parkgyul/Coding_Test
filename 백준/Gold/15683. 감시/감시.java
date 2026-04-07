import java.io.BufferedReader;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class Main{
    static int N, M;
    static int[][] arr;
    static int[][] covered;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][][] dirs = {
            {},
            {{0}, {1}, {2}, {3}},
            {{0,2}, {1,3}},
            {{0,1}, {1,2}, {2,3}, {3,0}},
            {{0,1,2}, {1,2,3}, {2,3,0}, {3,0,1}},
            {{0,1,2,3}}
    };
    static List<CCTV> cctvs;
    static int blindSpot = 0;
    static int minBlind = Integer.MAX_VALUE;
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        covered = new int[N][M];
        cctvs = new ArrayList<>();

        for(int i =0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());

                if(arr[i][j] == 0){
                    blindSpot ++;
                }else if(1 <= arr[i][j] && arr[i][j] <= 5){
                    cctvs.add(new CCTV(i, j, arr[i][j]));
                }
            }
        }

        dfs(0);
        System.out.print(minBlind);
    }
    public static void dfs(int index){

        if(index == cctvs.size()){
            minBlind = Math.min(minBlind, blindSpot);
            return;
        }

        CCTV cctv = cctvs.get(index);

        for(int[] d : dirs[cctv.type]){
            List<Point> w = watch(d, cctv);
            dfs(index+1);
            rollback(w);
        }
    }

    public static List<Point> watch(int[] dirs, CCTV cctv){
        List<Point> w = new ArrayList<>();

        for(int d : dirs){
            int ni = cctv.i + dx[d];
            int nj = cctv.j + dy[d];
            while(isIn(ni, nj) && arr[ni][nj] != 6){
                if(arr[ni][nj] == 0){
                    if(covered[ni][nj] == 0){
                        blindSpot--;
                    }
                    covered[ni][nj] ++;
                    w.add(new Point(ni, nj));
                }

                ni += dx[d];
                nj += dy[d];
            }
        }

        return w;
    }

    public static void rollback(List<Point> w){
        for(Point p : w){
            covered[p.i][p.j] --;
            if(covered[p.i][p.j] == 0){
                blindSpot ++;
            }
        }
    }

    public static boolean isIn(int i, int j){
        return i >= 0 && i < N && j >= 0 && j < M;
    }
    public static class Point{
        int i, j;
        public Point(int i, int j){
            this.i = i;
            this.j = j;
        }
    }

    public static class CCTV{
        int i, j, type;

        public CCTV(int i, int j, int type){
            this.i = i;
            this.j = j;
            this.type = type;
        }
    }
}