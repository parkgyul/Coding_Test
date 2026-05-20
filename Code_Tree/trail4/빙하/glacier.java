import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] canMelt;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        canMelt = new boolean[N][M];

        int ice = 0;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) ice++;
            }
        }

        List<Point> waters = new ArrayList<>();
        waters.add(new Point(0,0));
        findWaters(waters);
        
        int time = 0;
        int size = 0;
        int lastSize = 0;
        while(ice > 0){
            time++;

            size = 0;
            waters = new ArrayList<>();

            for(int i =0 ; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(map[i][j] == 1 && isPossibleToMelt(i, j)){
                        map[i][j] = 0;
                        size++;
                        waters.add(new Point(i, j));
                    }
                }
            }

            findWaters(waters);

            lastSize = size;
            ice -= size;
        }

        System.out.print(time + " " + lastSize);
    }

    static boolean isPossibleToMelt(int r, int c){
        for(int i =0 ; i < 4; i++){
            int ni = r + dx[i];
            int nj = c + dy[i];

            if(ni < 0 || ni >= N || nj < 0 || nj >= M) continue;

            if(canMelt[ni][nj]) return true;
        }

        return false;
    }


    static void findWaters(List<Point> points){
        Queue<Point> q = new LinkedList<>();
        for(Point p : points){
            canMelt[p.i][p.j] = true;
            q.add(p);
        }

        while(!q.isEmpty()){
            Point cur = q.poll();

            for(int i =0 ; i < 4; i++){
                int ni = cur.i + dx[i];
                int nj = cur.j + dy[i];

                if(ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
                if(canMelt[ni][nj] || map[ni][nj] == 1) continue;

                canMelt[ni][nj] = true;
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