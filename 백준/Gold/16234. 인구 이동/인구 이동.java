import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main{

    static int[][] arr;
    static int[][] copy;
    static boolean[][] opened;
    static int N, L, R;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][N];


        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean flag;
        int total = 0;
        while(true){
            opened = new boolean[N][N];
            copy = new int[N][N];
            flag = false;
            for(int i =0; i < N; i++){
                for(int j = 0; j <N; j++){
                    if(opened[i][j])
                        continue;

                    if(bfs(i, j))
                        flag = true;

                }
            }
            if(!flag)
                break;

            total++;
            for(int i = 0; i < N; i++){
                arr[i] = copy[i].clone();
            }
        }
        System.out.print(total);
    }

    public static boolean bfs(int i, int j){
        Queue<Point> q = new LinkedList<>();
        Queue<Point> change = new LinkedList<>();

        q.add(new Point(i, j));
        change.add(new Point(i, j));

        int sum = arr[i][j];
        int count = 1;
        opened[i][j] = true;
        boolean flag = false;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            for (int a = 0; a < 4; a++) {
                int next_i = cur.i + dx[a];
                int next_j = cur.j + dy[a];

                if (next_i < 0 || next_i >= N || next_j < 0 || next_j >= N)
                    continue;

                if(opened[next_i][next_j])
                    continue;

                int dif = Math.abs(arr[cur.i][cur.j] - arr[next_i][next_j]);

                if(dif >= L && dif <= R){
                    opened[next_i][next_j] = true;
                    flag = true;
                    q.add(new Point(next_i, next_j));
                    change.add(new Point(next_i, next_j));
                    sum += arr[next_i][next_j];
                    count ++;
                }

            }
        }

        sum /= count;
        while(!change.isEmpty()){
            Point cur = change.poll();
            copy[cur.i][cur.j] = sum;
        }

        return flag;
    }

    public static class Point{
        int i, j;
        public Point(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
}