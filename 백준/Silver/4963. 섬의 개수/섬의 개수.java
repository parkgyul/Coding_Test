import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
    static int[][] arr;
    static int w, h;
    static boolean[][] visited;

    static int[] dx = {-1, 0, 0, 1, 1, 1, -1, -1};
    static int[] dy = {0, -1, 1, 0, -1, 1 , 1, -1};
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h =  Integer.parseInt(st.nextToken());


        while(w != 0 && h != 0){
            arr = new int[h][w];
            visited = new boolean[h][w];
            for(int i = 0; i< h; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j<w; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    visited[i][j] = false;
                }
            }
            int cnt = 0;
            for(int i = 0; i < h; i++){
                for(int j = 0; j<w; j++){
                    if(arr[i][j] == 1 && !visited[i][j]){
                        bfs(i, j);
                        cnt++;
                    }
                }
            }
            System.out.println(cnt);
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h =  Integer.parseInt(st.nextToken());
        }
    }

    public static void bfs(int i, int j){
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(i, j));

        while(!q.isEmpty()){
            Point current = q.poll();
            int current_i = current.i;
            int current_j = current.j;

            for(int k = 0; k < 8; k++){
                int next_i = current_i + dx[k];
                int next_j = current_j + dy[k];

                if(next_i<0 || next_i >= h || next_j <0 || next_j >= w)
                    continue;
                if(arr[next_i][next_j] == 0)
                    continue;
                if(visited[next_i][next_j])
                    continue;

                q.add(new Point(next_i, next_j));
                visited[next_i][next_j] = true;

            }
        }
    }

    public static class Point{
        private int i;
        private int j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

}