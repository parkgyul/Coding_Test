import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
public class Main{

    static int[][][] tomato;
    static int h,n,m;
    static int cnt;

    static int[] di = {0,0,0,0,1,-1};
    static int[] dj = {1,-1,0,0,0,0};
    static int[] dk = {0,0,1,-1,0,0};
    static Queue<Point> q = new LinkedList<Point>();
    static int tomatos = 0;
    static int ripen_tomato = 0;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        tomato = new int[h][n][m];
        for(int i = 0; i<h; i++) {
            for(int j = 0; j < n; j++){
                st = new StringTokenizer(br.readLine());
                for(int k = 0; k < m; k++){
                    int num = Integer.parseInt(st.nextToken());
                    tomato[i][j][k] = num;
                    if(num == 1) q.add(new Point(i, j, k, 0));
                    if(num != -1) {
                        tomatos ++;
                    }
                }
            }
        }

        while(!q.isEmpty()) {
            Point current = q.poll();
            cnt = current.date;
            ripen_tomato ++;
            if(tomatos == ripen_tomato) {
                System.out.println(current.date);
                return;
            }
            for (int i = 0; i < 6; i++) {
                int next_i = current.i + di[i];
                int next_j = current.j + dj[i];
                int next_k = current.k + dk[i];

                if (next_i < 0 || next_i >= h || next_j < 0 || next_j >= n || next_k < 0 || next_k >= m)
                    continue;
                if (tomato[next_i][next_j][next_k] == 0) {
                    q.add(new Point(next_i, next_j, next_k, current.date + 1));
                    tomato[next_i][next_j][next_k] = 1;
                }
            }
        }
        System.out.println(-1);
    }


    static class Point{
        private int i, j, k, date;
        public Point(int i, int j, int k, int date){
            this.i = i;
            this.j = j;
            this.k = k;
            this.date = date;
        }
    }

}