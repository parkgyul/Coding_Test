import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

class Main
{
    static int min = Integer.MAX_VALUE;
    static Queue<Point> q = new LinkedList<>();
    static int[][] arr;
    static int n, m;
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n+1][m];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m ; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i < m; i++){
            q.add(new Point(0, i, 10, arr[0][i]));
            bfs();
        }
        System.out.println(min);

    }
    public static void bfs(){

        while(!q.isEmpty()){
            Point current = q.poll();

            if(current.i == n-1) min = Math.min(min, current.sum);

            for(int i = -1; i <= 1; i++){

                if(i == current.prev_dir)
                    continue;

                int next_j = current.j + i;

                if(next_j < 0 || next_j >= m || current.i+1 >= n)
                    continue;

                q.add(new Point(current.i+1, next_j, i, current.sum + arr[current.i+1][next_j]));
            }

        }
    }
    static class Point{
        private int i, j, prev_dir,sum;
        public Point(int i, int j, int prev_dir, int sum){
            this.i = i;
            this.j = j;
            this.prev_dir = prev_dir;
            this.sum = sum;
        }
    }
}