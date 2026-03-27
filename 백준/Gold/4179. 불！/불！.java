import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    static int R, C;
    static char[][] arr;
    static Queue<Point> q = new LinkedList<>();
    static Point JH;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int[][] fires;
    static int[][] jhTimes;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[R][C];


        fires = new int[R][C];
        jhTimes = new int[R][C];


        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            Arrays.fill(fires[i], -1);
            Arrays.fill(jhTimes[i], -1);

            for (int j = 0; j < C; j++) {
                arr[i][j] = str.charAt(j);
                if (arr[i][j] == 'F'){
                    q.add(new Point( i, j, 0));
                    fires[i][j] = 0;
                } else if(arr[i][j] == 'J'){
                    JH = new Point(i, j, 0);
                    jhTimes[i][j] = 0;
                }
            }
        }

        fire();
        jh();
    }

    public static void fire(){
        while(!q.isEmpty()){
            Point cur = q.poll();

            for(int i = 0; i < 4; i++){
                int next_i = cur.i + dx[i];
                int next_j = cur.j + dy[i];

                if(next_i < 0 || next_i >= R || next_j < 0 || next_j >= C)
                    continue;
                if(arr[next_i][next_j] == '#')
                    continue;
                if(fires[next_i][next_j] != -1)
                    continue;

                q.add(new Point(next_i, next_j, cur.cnt+1));
                fires[next_i][next_j] = cur.cnt+1;
            }
        }
    }

    public static void jh(){
        q.add(JH);

        while(!q.isEmpty()){
            Point cur = q.poll();

            for(int i = 0; i < 4; i++){
                int next_i = cur.i + dx[i];
                int next_j = cur.j + dy[i];

                if(next_i < 0 || next_i >= R || next_j < 0 || next_j >= C) {
                    System.out.print(cur.cnt+1);
                    return;
                }

                if(arr[next_i][next_j] == '#') continue;

                if(jhTimes[next_i][next_j] != -1) continue;

                if(fires[next_i][next_j] != -1 && fires[next_i][next_j] <= cur.cnt+1)
                    continue;

                jhTimes[next_i][next_j] = cur.cnt+1;
                q.add(new Point(next_i, next_j, cur.cnt+1));
            }
        }

        System.out.print("IMPOSSIBLE");
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