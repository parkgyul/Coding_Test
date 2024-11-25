import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

class Main{
    static int r, c;
    static char[][] arr;
    static Queue<Point> s = new LinkedList<>();
    static Queue<Point> water = new LinkedList<>();
    static int[] di = {0, 0, -1, 1};
    static int[] dj = {1, -1, 0, 0};

    static boolean isPossible = false;
    static int current_time = -1;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new char[r][c];


        for(int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                char c = str.charAt(j);
                arr[i][j] = c;
                if (c == 'S') s.add(new Point(i, j, 0));
                if (c == '*') water.add(new Point(i, j, 0));
            }
        }

        bfs();
        if(!isPossible) System.out.println("KAKTUS");
    }
    public static void bfs(){
        while(!s.isEmpty()){
            Point current = s.poll();

            if(current.time != current_time){
                current_time = current.time;
                int current_water = water.size();
                for(int i = 0; i<current_water; i++){
                    water();
                }
            }

            for(int i = 0; i<4; i++){
                int next_i = current.i + di[i];
                int next_j = current.j + dj[i];

                if(next_i <0 || next_i >= r || next_j < 0 || next_j >= c)
                    continue;

                if(arr[next_i][next_j] == '*' || arr[next_i][next_j] == 'X'|| arr[next_i][next_j] == 'S')
                    continue;

                if(arr[next_i][next_j] == 'D'){
                    System.out.println(current.time+1);
                    isPossible = true;
                    return;
                }
                s.add(new Point(next_i, next_j, current.time + 1 ));
                arr[next_i][next_j] = 'S';

            }
        }
    }
    public static void water(){
        Point current_water = water.poll();
        for(int i = 0; i<4; i++){
            int next_water_i = current_water.i + di[i];
            int next_water_j = current_water.j + dj[i];

            if(next_water_i <0 || next_water_i >= r || next_water_j < 0 || next_water_j >= c)
                continue;
//            if(arr[next_water_i][next_water_j] == 'D' || arr[next_water_i][next_water_j] == 'S' || arr[next_water_i][next_water_j] == '*')
//                continue;
            if(arr[next_water_i][next_water_j] == '.' || arr[next_water_i][next_water_j] == 'S' ){
                arr[next_water_i][next_water_j] = '*';
                water.add(new Point(next_water_i, next_water_j, 0));
            }

        }
    }

    static class Point{
        private int i,j, time;

        public Point(int i, int j, int time ){
            this.i = i;
            this.j = j;
            this.time = time;
        }
    }
}