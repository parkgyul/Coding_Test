import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        Queue<Point> q = new LinkedList<>();
        int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
        int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 1){
                    q.add(new Point(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        int max = 0;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                while(!q.isEmpty()){
                    Point current = q.poll();
                    max = current.cnt;

                    for(int a = 0; a <8; a++){
                        int next_i = current.i + dx[a];
                        int next_j = current.j + dy[a];

                        if(next_i < 0 || next_j < 0 || next_i >=n || next_j >= m)
                            continue;
                        if(visited[next_i][next_j])
                            continue;

                        q.add(new Point(next_i, next_j, current.cnt+1));
                        visited[next_i][next_j] = true;
                    }
                }
            }
        }

        System.out.print(max);
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