import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
    static int N;
    static int[][] arr;
    static int eat = 0;
    static int size = 2;
    static int si, sj;
    static int time = 0;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());

                if(arr[i][j] == 9){
                    si = i;
                    sj = j;
                    arr[i][j] = 0;
                }
            }
        }

        bfs();

        System.out.println(time);
    }

    public static void bfs(){
        while(true){
            PriorityQueue<Point> pq = new PriorityQueue<>();
            boolean[][] visited = new boolean[N][N];

            pq.add(new Point(si, sj, 0));
            visited[si][sj] = true;

            boolean ck = false;

            while(!pq.isEmpty()){
                Point cur = pq.poll();
                int ci = cur.i;
                int cj = cur.j;

                if(arr[ci][cj] != 0 && arr[ci][cj] < size){
                    arr[ci][cj] = 0;
                    si = ci;
                    sj = cj;
                    eat++;
                    time += cur.d;
                    ck = true;
                    break;
                }

                for(int i = 0; i < 4; i++){
                    int ni = ci + dx[i];
                    int nj = cj + dy[i];

                    if(ni < 0 || ni >= N || nj < 0 || nj >= N || visited[ni][nj])
                        continue;

                    if(arr[ni][nj] > size)
                        continue;

                    pq.add(new Point(ni, nj, cur.d + 1));
                    visited[ni][nj] = true;
                }
            }

            if(!ck)
                break;

            if(size == eat){
                size ++;
                eat = 0;
            }
        }
    }

    public static class Point implements Comparable<Point>{
        int i, j, d;

        public Point(int i, int j, int d) {
            this.i = i;
            this.j = j;
            this.d = d;
        }

        public int compareTo(Point o){
            if(this.d != o.d) return this.d -o.d;
            else if(this.i != o.i) return this.i - o.i;
            return this.j - o.j;
        }
    }
}