import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Main{
    static int n, m;
    static boolean[][] visited;
    static int[][] arr;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    static int min = 0;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        visited = new boolean[n][m];

        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < m; j++){
                arr[i][j] = str.charAt(j) - '0';
                visited[i][j] = false;
            }
        }

        bfs();

        System.out.println(min);

    }
    public static void bfs(){
        Queue<Point> q = new LinkedList<>();

        q.add(new Point(0,0,1));
        visited[0][0] = true;

        while(!q.isEmpty()){
            Point current = q.poll();
            if(current.i == n-1 && current.j == m-1){
                min = current.cnt;
                break;
            }

            for(int i = 0; i < 4; i++){
                int next_i = current.i + dx[i];
                int next_j = current.j + dy[i];

                if(next_i <0 || next_i >= n || next_j <0 || next_j >= m)
                    continue;
                if(arr[next_i][next_j] == 0 || visited[next_i][next_j])
                    continue;

                q.add(new Point(next_i, next_j, current.cnt+1));
                visited[next_i][next_j] = true;
            }


        }
    }

    public static class Point{
        private int i, j, cnt;
        public Point(int i, int j, int cnt){
            this.i = i;
            this.j = j;
            this.cnt = cnt;
        }
    }
}