import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    static int N, M;
    static boolean[][] air;
    static int[][] arr;
    static int cheeseCnt = 0;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        air = new boolean[N][M];
        arr = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 1) cheeseCnt++;
            }
        }

        List<Point> start = new ArrayList<>();
        start.add(new Point(0,0));
        findAir(start);

        int time = 0;

        while(true) {
            time++;
            start = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] == 1 && isOutSide(i, j)) {
                        start.add(new Point(i, j));
                        cheeseCnt--;
                    }
                }
            }

            findAir(start);

            if (cheeseCnt == 0)
                break;
        }

        System.out.print(time);
    }

    public static boolean isOutSide(int i, int j){
        int outSide = 0;

        for(int a = 0; a < 4; a++){
            int ni = i + dx[a];
            int nj = j + dy[a];

            if(ni < 0 || ni >= N || nj < 0 || nj >= M)
                continue;

            if(air[ni][nj]) outSide ++;
        }

        return outSide >= 2;
    }

//    public static List<Point> bfs(int i, int j){
//        List<Point> melted = new ArrayList<>();
//        Queue<Point> q = new LinkedList<>();
//
//        q.add(new Point(i, j));
//        visited[i][j] = true;
//
//        while(!q.isEmpty()){
//            Point cur = q.poll();
//
//            int airCnt = 0;
//            for(int a = 0; a < 4; a++){
//                int ni = cur.i + dx[a];
//                int nj = cur.j + dy[a];
//
//                if(ni >= N || ni < 0 || nj >= M || nj < 0)
//                    continue;
//
//                if(air[ni][nj]){ // 공기 갯수 세기
//                    airCnt++;
//                }
//
//                if(!visited[ni][nj] && arr[ni][nj] == 1){ // 치즈면
//                    visited[ni][nj] = true;
//                    q.add(new Point(ni, nj));
//                }
//            }
//
//            if(airCnt >= 2){
//                melted.add(new Point(cur.i, cur.j));
//                cheeseCnt--;
//            }
//        }
//
//        return melted;
//    }

    public static void findAir(List<Point> points){
        Queue<Point> q = new LinkedList<>();

        for(Point p : points){
            q.add(new Point(p.i, p.j));
            arr[p.i][p.j] = 0;
            air[p.i][p.j] = true;
        }

        while(!q.isEmpty()){
            Point cur = q.poll();

            for(int i = 0; i < 4; i++){
                int ni = cur.i + dx[i];
                int nj = cur.j + dy[i];

                if(ni >= N || ni < 0 || nj >= M || nj < 0)
                    continue;

                if(air[ni][nj] || arr[ni][nj] == 1)
                    continue;

                air[ni][nj] = true;
                q.add(new Point(ni, nj));
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

/*
8 9
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 1 1 0 0 0 1 1 0
0 1 0 1 1 1 0 1 0
0 1 0 0 1 0 0 1 0
0 1 0 1 1 1 0 1 0
0 1 1 0 0 0 1 1 0
0 0 0 0 0 0 0 0 0
 */
