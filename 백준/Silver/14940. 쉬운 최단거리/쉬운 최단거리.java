import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Main{
    static int n, m;
    static int[][] arr;
    static int[] dx = {-1,0,0,1};
    static int[] dy = {0,1,-1,0};
    static int[][] visited;
    static int start_i, start_j;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        visited = new int[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                int num = Integer.parseInt(st.nextToken());
                arr[i][j] = num;
                if(num == 0) visited[i][j] = 0;
                else visited[i][j] = -1;
                if(num == 2) {
                    start_i = i;
                    start_j = j;
                }
            }
        }

        bfs();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m ; j++){
                System.out.print(visited[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void bfs(){
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(start_i,start_j,0));
        visited[start_i][start_j] = 0;
        while(!q.isEmpty()){
            Point current = q.poll();

            for(int i = 0; i<4; i++){
                int next_i = current.i + dx[i];
                int next_j = current.j + dy[i];

                if(next_i <0 || next_i >= n || next_j <0 || next_j >= m)
                    continue;
                if(arr[next_i][next_j] == 0)
                    continue;
                if(visited[next_i][next_j] != -1)
                    continue;

                q.add(new Point(next_i, next_j, current.cnt+1));
                visited[next_i][next_j] = current.cnt+1;
            }
        }
    }

    public static class Point{
        int i, j, cnt;
        public Point(int i, int j, int cnt){
            this.i = i;
            this.j = j;
            this.cnt = cnt;
        }
    }
}