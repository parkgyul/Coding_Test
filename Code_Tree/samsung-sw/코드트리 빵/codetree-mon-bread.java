import java.io.*;
import java.util.*;
public class Main {
    static int n, m;
    static int[][] Bmap;
    static int[] CR, CC;
    static boolean[] arrived;
    static int[][] Cmap;
    static int finished;
    static int t;
    static int[] PR, PC;
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 베이스캠프 위치 정보
        Bmap = new int[n+1][n+1];
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++){
                Bmap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        CR = new int[m+1];
        CC = new int[m+1];
        PR = new int[m+1];
        PC = new int[m+1];
        arrived = new boolean[m+1];
        Cmap = new int[n+1][n+1];

        for(int i = 1; i <= m; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            CR[i] = r;
            CC[i] = c;
            Cmap[r][c] = i;
        }

        t = 1;
        finished = 0;
        while(finished < m){
            // 1. 이동 & 편의점 도착
            move();
            
            // 2. 베이스 캠프 배치
            if(t <= m){
                int[] baseCamp = findBaseCamp(t);
                placeToBaseCamp(baseCamp, t);
            }
            t++;
        }

        System.out.print(t-1);
    }

    static int[][] findShortest(int idx){
        int cr = CR[idx];
        int cc = CC[idx];

        int[][] distance = new int[n+1][n+1];
        for(int i = 1; i <= n; i++){
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }
        distance[cr][cc] = 0;

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{cr, cc, 0});

        while(!q.isEmpty()){
            int[] cur = q.poll();

            for(int d = 0; d < 4; d++){
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];

                if(isOutside(nr, nc)) continue;
                // 이미 도착한 베이스 캠프 or 이미 도착한 편의점
                if(Bmap[nr][nc] == -1 || Cmap[nr][nc] == -1) continue;
                if(distance[nr][nc] <= cur[2]+1) continue;

                distance[nr][nc] = cur[2]+1;
                q.add(new int[]{nr, nc, cur[2]+1});
            }
        }
        return distance;
    }

    static void move(){
        List<Integer> arrivedPeople = new ArrayList<>();
        for(int idx = 1; idx < Math.min(t, m+1); idx++){
            // 이미 도착한 사람을 볼 필요가 없음.
            if(arrived[idx]) continue;

            int[][] distance = findShortest(idx);
            int bestD = Integer.MAX_VALUE;
            int bestDir = -1;
            int r = PR[idx];
            int c = PC[idx];

            // 최단거리로 움직이며 최단 거리로 움직이는 방법이 여러가지라면 ↑, ←, →, ↓ 의 우선 순위로 움직이게 됩니다.
            for(int d = 0; d < 4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];

                if(isOutside(nr, nc)) continue;

                if(distance[nr][nc] < bestD){
                    bestD = distance[nr][nc];
                    bestDir = d;
                }
            }

            // 사람 이동
            PR[idx] += dr[bestDir];
            PC[idx] += dc[bestDir];

            // 가고자 했던 편의점이라면?
            if(Cmap[PR[idx]][PC[idx]] == idx){
                arrivedPeople.add(idx);
            }

        }
        
        //격자에 있는 사람들이 모두 이동한 뒤에 해당 칸을 지나갈 수 없어짐에 유의합니다.
        for(int idx : arrivedPeople){
            // 방문 처리
            Cmap[PR[idx]][PC[idx]] = -1;
            arrived[idx] = true;
            finished++;
        }
    }

    static void placeToBaseCamp(int[] baseCamp, int idx){
        int r = baseCamp[0];
        int c = baseCamp[1];

        Bmap[r][c] = -1; // 이미 방문된 베이스 캠프 

        PR[idx] = r;
        PC[idx] = c;
    }


    static int[] findBaseCamp(int idx){
        // 가려는 편의점 위치
        int cr = CR[idx];
        int cc = CC[idx];

        int bestD = Integer.MAX_VALUE;
        int bestR = Integer.MAX_VALUE;
        int bestC = Integer.MAX_VALUE;

        int[][] distance = new int[n+1][n+1];
        for(int i = 1; i <= n; i++){
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }
        distance[cr][cc] = 0;

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{cr, cc, 0});

        while(!q.isEmpty()){
            int[] cur = q.poll();

            if(Bmap[cur[0]][cur[1]] == 1){ // 베이스 캠프라면?
                if(bestD > cur[2]){
                    bestD = cur[2];
                    bestR = cur[0];
                    bestC = cur[1];
                }else if(bestD == cur[2]){
                    if(bestR > cur[0]){
                        bestR = cur[0];
                        bestC = cur[1];
                    }else if(bestR == cur[0] && bestC > cur[1]){
                        bestC = cur[1];
                    }
                }
            }

            for(int d = 0; d < 4; d++){
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];

                if(isOutside(nr, nc)) continue;
                if(distance[nr][nc] <= cur[2]+1) continue;
                if(Bmap[nr][nc] == -1 || Cmap[nr][nc] == -1) continue;

                distance[nr][nc] = cur[2]+1;
                q.add(new int[]{nr, nc, cur[2]+1});
            }
        }

        return new int[]{bestR, bestC};
    }

    static void print(String title){
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                System.out.print(Bmap[i][j] + " ");
            }
            System.out.println();
        }
    }

    static boolean isOutside(int r, int c){
        return r < 1 || r > n || c < 1 || c > n;
    }
}