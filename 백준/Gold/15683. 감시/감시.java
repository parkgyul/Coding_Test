import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    static int N, M;
    static int[][] arr;
    static int[][] cover;
    static int blindSpot = 0;
    static int minBlind = Integer.MAX_VALUE;
    static List<CCTV> cctvs = new ArrayList<>();

    static int[] dx = {-1, 0, 1, 0}; // 상, 우, 하, 좌
    static int[] dy = {0, 1, 0, -1};
    static int[][][] dirs = {
            {},
            {{0}, {1}, {2}, {3}},
            {{0,2}, {1,3}},
            {{0,1}, {1,2}, {2,3}, {3,0}},
            {{0,1,2}, {1,2,3}, {2,3,0}, {3,0,1}},
            {{0,1,2,3}}
    };

    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        cover = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());

                if(arr[i][j] == 0){
                    blindSpot++;
                }else if(1 <= arr[i][j] && arr[i][j] <= 5){
                    cctvs.add(new CCTV(i, j, arr[i][j]));
                }
            }
        }

        dfs(0);
        System.out.println(minBlind);
    }

    public static void dfs(int index){

        if(index == cctvs.size()){
            minBlind = Math.min(blindSpot, minBlind);
            return;
        }

        CCTV cur = cctvs.get(index);

        for(int[] dirSet : dirs[cur.type]){
            List<Point> changed = watch(cur.i, cur.j, dirSet);
            dfs(index+1);
            rollback(changed);
        }
    }

    static List<Point> watch(int i, int j, int[] dirSet){
        List<Point> changed = new ArrayList<>();

        for(int d : dirSet){
            int ni = i + dx[d];
            int nj = j + dy[d];

            while(isIn(ni, nj) && arr[ni][nj] != 6){
                if(arr[ni][nj] == 0){
                    if(cover[ni][nj] == 0){
                        blindSpot --;
                    }

                    changed.add(new Point(ni, nj));
                    cover[ni][nj] ++;
                }
                ni += dx[d];
                nj += dy[d];
            }
        }

        return changed;
    }

    static void rollback(List<Point> changed){
        for(Point p : changed){
            cover[p.i][p.j]--;
            if(cover[p.i][p.j] == 0){
                blindSpot++;
            }
        }
    }

    static boolean isIn(int i, int j){
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