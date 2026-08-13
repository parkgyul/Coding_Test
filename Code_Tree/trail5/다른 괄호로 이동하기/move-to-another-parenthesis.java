import java.io.*;
import java.util.*;

public class Main {
    static int N, A, B;
    static int[][] arr;
    static int[][]costs;
    static int max = Integer.MIN_VALUE;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        arr = new int[N][N];

        for(int i = 0; i < N; i++){
            String str = br.readLine();
            for(int j = 0; j < N; j++){
                char ch = str.charAt(j);
                if(ch == '('){
                    arr[i][j] = 0;
                }else{
                    arr[i][j] = 1;
                }
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                bfs(i, j);
            }
        }

        System.out.print(max);
    }

    static void bfs(int x, int y){
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(x, y, 0));
        costs = new int[N][N];

        for(int i = 0; i < N; i++){
            Arrays.fill(costs[i], Integer.MAX_VALUE);
        }

        costs[x][y] = 0;

        while(!pq.isEmpty()){
            Point cur = pq.poll();
        
            if(costs[cur.i][cur.j] < cur.cost) continue;

            max = Math.max(max, cur.cost);

            for(int i = 0; i < 4; i++){
                int ni = cur.i + dx[i];
                int nj = cur.j + dy[i];

                if(ni < 0 || ni >= N || nj < 0 || nj >= N) continue;

                int nextCost = cur.cost + (arr[cur.i][cur.j] == arr[ni][nj]? A : B);

                if(nextCost >= costs[ni][nj]) continue;

                costs[ni][nj] = nextCost;
                pq.add(new Point(ni, nj, nextCost));
            }
        }
    }

    static class Point implements Comparable<Point>{
        int i, j, cost;

        Point(int i, int j, int cost){
            this.i = i;
            this.j = j;
            this.cost = cost;
        }

        public int compareTo(Point o){
            return this.cost - o.cost;
        }
    }


}