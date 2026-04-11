import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

public class Main{
    static int N, M, F;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1 , 1, 0};
    static int[][] arr;
    static int si, sj;
    static int[][] clients;
    static int[] times;

    static int[] destR;
    static int[] destC;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());

        arr = new int[N+1][N+1];

        for(int i = 1; i <= N; i++){
            st =  new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        si = Integer.parseInt(st.nextToken());
        sj = Integer.parseInt(st.nextToken());


        clients = new int[N+1][N+1];
        times = new int[M+1];
        destR = new int[M+1];
        destC = new int[M+1];

        for(int i =1; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            clients[r1][c1] = i;

            destR[i] = r2;
            destC[i] = c2;

            times[i] = bfs(r1, c1, r2, c2); // 승객 타는 시간
        }

        int cnt = M;
        while(cnt --> 0){
            Point clientPosition = findClient();

            if(clientPosition.i == Integer.MAX_VALUE){ // 태울 승객 못 찾음.
                System.out.print(-1);
                return;
            }

            F -= clientPosition.cnt; // 승객 태우러 가는 데 걸리는 시간
            int clientNum = clients[clientPosition.i][clientPosition.j];
            int time = times[clientNum]; // 승객 태우고 출발 -> 도착까지 걸리는 시간

            if (time == -1) { // 승객 태워줄 방법 x
                System.out.print(-1);
                return;
            }

            F -= time; // 승객 타고 난 후..
            if(F < 0){ // 승객 타고 난 후 시간이 없으면
                System.out.print(-1);
                return;
            }

            si = destR[clientNum];
            sj = destC[clientNum];
            F += time*2;

            clients[clientPosition.i][clientPosition.j] = 0;
        }

        System.out.print(F);
    }

    public static int bfs(int r1, int c1, int r2, int c2){
        if(r1 == r2 && c1 == c2){
            return 0;
        }
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N+1][N+1];

        q.add(new Point(r1, c1, 0));
        visited[r1][c1] = true;

        while(!q.isEmpty()){
            Point cur = q.poll();

            for(int i =0 ; i < 4; i++){
                int ni = cur.i + dx[i];
                int nj = cur.j + dy[i];

                if(ni < 1 || ni > N || nj < 1 || nj > N || visited[ni][nj]) continue;

                if(arr[ni][nj] == 1) continue; // 벽

                if(ni == r2 && nj == c2){
                    return cur.cnt+1;
                }

                visited[ni][nj] = true;
                q.add(new Point(ni, nj, cur.cnt+1));
            }
        }

        return -1;
    }

    public static Point findClient(){
        if(clients[si][sj] >= 1 && clients[si][sj] <= M){
            return new Point(si, sj, 0);
        }

        boolean[][] visited = new boolean[N+1][N+1];
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(si, sj, 0));
        visited[si][sj] = true;

        int minCnt = Integer.MAX_VALUE;
        int pi = Integer.MAX_VALUE , pj = Integer.MAX_VALUE;

        while(!q.isEmpty()){
            Point cur = q.poll();

            if (cur.cnt > minCnt) break;
            if (cur.cnt >= F) continue;

            for(int i = 0; i < 4; i++){
                int ni = cur.i + dx[i];
                int nj = cur.j + dy[i];

                if(ni < 1 || ni > N || nj < 1 || nj > N || visited[ni][nj]) continue;

                if(arr[ni][nj] == 1) continue; // 벽

                if(clients[ni][nj] > 0 && clients[ni][nj] <= M){// 승객 찾음.
                    if(minCnt > cur.cnt+1){
                        minCnt = cur.cnt+1;
                        pi = ni;
                        pj = nj;
                    }else if(minCnt == cur.cnt+1){
                        if(pi > ni){
                            pi = ni;
                            pj = nj;
                        }else if(pi == ni && pj > nj){
                            pj = nj;
                        }
                    }
                }

                visited[ni][nj] = true;
                q.add(new Point(ni, nj, cur.cnt+1));
            }
        }

        return new Point(pi, pj, minCnt);
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