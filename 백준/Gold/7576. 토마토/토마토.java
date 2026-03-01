import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

class Main{
    static int n, m;
    static int tomato, ripen_tomato;
    static int[][] arr;
    static Queue<Point> q = new LinkedList<>();
    static int max = 0, cnt = 0;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, 1, -1, 0};
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken()); // 가로
        n = Integer.parseInt(st.nextToken()); // 세로

        arr = new int[n][m];
        tomato = 0; // 되어야하는 토마토 수
        ripen_tomato = 0;

        for(int i =0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] != -1) tomato ++;
                if(arr[i][j] == 1){
                    q.add(new Point(i, j, 0));
                }
            }
        }

        while(!q.isEmpty()){
            Point current = q.poll();
            ripen_tomato++;
            if(tomato == ripen_tomato){
                System.out.println(current.t);
                return;
            }

            for(int i = 0; i < 4; i++){
                int next_i = current.i + dx[i];
                int next_j = current.j + dy[i];

                if(next_i <0 || next_j <0 || next_i >= n || next_j >= m)
                    continue;
                if(arr[next_i][next_j] == -1 || arr[next_i][next_j] == 1)
                    continue;

                q.add(new Point(next_i, next_j, current.t+1));
                arr[next_i][next_j] = 1; // 토마토 익히기
            }
        }

        System.out.print(-1);
    }

    public static class Point{
        int i, j, t;

        public Point(int i, int j, int t){
            this.i = i;
            this.j = j;
            this.t = t;
        }
    }

}