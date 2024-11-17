import java.io.IOException;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;
public class Main{
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //가로
        int m = Integer.parseInt(st.nextToken());
        //세로
        int n = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];
        int tomato = 0;
        int ripen_tomato = 0;
        Queue<Point> q = new LinkedList<>();

        for(int i = 0; i<n; i++){
            st =  new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                int num = Integer.parseInt(st.nextToken());
                arr[i][j] = num;
                if(num == 1) {
                    q.add(new Point(j, i, 0));
                }
                if(num != -1) {
                    tomato ++;
                }
             }
        }


        while(!q.isEmpty()){
            Point current = q.poll();
            int current_x = current.x;
            int current_y = current.y;
            ripen_tomato ++;
            if(tomato == ripen_tomato) {
                System.out.println(current.cnt);
                return;
            }
            for(int i = 0; i<4; i++){
                int next_x = current_x+dx[i];
                int next_y = current_y+dy[i];

                if(next_x<0 || next_x>=m || next_y<0 || next_y>=n)
                    continue;
                if(arr[next_y][next_x]== -1 || arr[next_y][next_x] == 1)
                    continue;

                q.add(new Point(next_x, next_y, current.cnt+1));
                arr[next_y][next_x] = 1;
            }

        }

        System.out.println(-1);
    }
    public static class Point{
        private int x;
        private int y;
        private int cnt;

        public Point(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}