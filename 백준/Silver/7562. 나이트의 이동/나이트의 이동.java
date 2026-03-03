import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

class Main {
    static int[] dx = {-2, -1, 1 , 2, 2, 1, -1, -2};
    static int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       int tc = Integer.parseInt(br.readLine());

       StringBuilder sb = new StringBuilder();

       for(int i = 0; i < tc; i++){
           int l = Integer.parseInt(br.readLine()); // 한 변 길이
           Queue<Point> q = new LinkedList<>();
           boolean[][] visited = new boolean[l][l];

           StringTokenizer st = new StringTokenizer(br.readLine());
           int start_i = Integer.parseInt(st.nextToken());
           int start_j = Integer.parseInt(st.nextToken());

           st = new StringTokenizer(br.readLine());
           int end_i = Integer.parseInt(st.nextToken());
           int end_j = Integer.parseInt(st.nextToken());

           q.add(new Point(start_i, start_j, 0));
           visited[start_i][start_j] = true;
           while(!q.isEmpty()){
               Point current = q.poll();

               if(current.i == end_i && current.j == end_j){
                   sb.append(current.cnt).append("\n");
                   break;
               }

               for(int j = 0; j <8; j++){
                   int next_i = current.i + dx[j];
                   int next_j = current.j + dy[j];

                   if(next_i < 0 || next_j <0 || next_i >= l || next_j >= l)
                       continue;
                   if(visited[next_i][next_j])
                       continue;

                   q.add(new Point(next_i, next_j, current.cnt+1));
                   visited[next_i][next_j] = true;
               }
           }
       }
       
       System.out.print(sb);
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