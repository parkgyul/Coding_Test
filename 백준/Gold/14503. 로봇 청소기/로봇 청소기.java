import java.io.IOException;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.Queue;
import java.util.LinkedList;

class Main{
    static int n;
    static int m;
    static int[][] arr;
    static int[] dx = {-1, 0, 1, 0}; // 북동남서
    static int[] dy = {0, 1, 0, -1};

    static int cnt = 0;
    static Queue<Point> q = new LinkedList<>();

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        q.add(new Point(a, b, c));

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bfs();

        System.out.print(cnt);
    }

    public static void bfs(){
        while(!q.isEmpty()){
            Point current = q.poll();
            int visit_cnt = 0;

            if(arr[current.i][current.j] == 0){
                arr[current.i][current.j] = -1; // 청소함.
                cnt++;
            }

            for(int i = 0; i < 4; i++){
                int next_dir = (current.dir+3) % 4; // 반시계 90도
                int next_i = current.i + dx[next_dir];
                int next_j = current.j + dy[next_dir];

                current.dir = next_dir;

                if(arr[next_i][next_j] == 0 ){
                    q.add(new Point(next_i, next_j, current.dir));
                    break;
                }else{
                    visit_cnt ++;
                }

            }

            if(visit_cnt == 4){ // 사방 다 청소 되어 있음.
                int back_dir = (current.dir + 2) % 4;
                int back_i = current.i + dx[back_dir]; // 후진
                int back_j = current.j + dy[back_dir]; // 후진

                if(arr[back_i][back_j] == 1) // 벽
                    return;

                q.add(new Point(back_i, back_j, current.dir));
            }
        }

    }
    public static class Point{
        int i, j, dir;
        public Point(int i, int j, int dir){
            this.i = i;
            this.j = j;
            this.dir = dir;
        }
    }
}