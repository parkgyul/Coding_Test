import java.io.*;
import java.util.*; 

public class Main {
    static boolean DEBUG = false;
    static int N, M, K;
    static int[][] map; // 0: 빈칸, 1~9 : 벽 (idx : 1 ~ N)
    static int[] R, C; // 사람들 위치(idx: 1 ~ M)
    static int exitR, exitC; // 출구 위치 
    static boolean[] escaped;
    static int finished;
    static int moved;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        R = new int[M+1];
        C = new int[M+1];
        for(int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            R[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        exitR = Integer.parseInt(st.nextToken());
        exitC = Integer.parseInt(st.nextToken());
        print("[INIT]");
        printPeople("이동 전 사람");
        
        moved = 0;
        escaped = new boolean[M+1];

        while(K-->0){
            // 1. 참가자 이동
            // int[][] distance = bfs();
            move();
            // printPeople("이동 후 사람");

            if(finished >= M){
                break;
            }

            // 2. 미로 회전
            rotate(findRotation());
        }

        System.out.println(moved);
        System.out.println(exitR +" " +exitC);
    }

    static int[] findRotation(){
        int bestSize = Integer.MAX_VALUE;
        int bestR = Integer.MAX_VALUE;
        int bestC = Integer.MAX_VALUE;

        for(int idx = 1; idx <= M; idx++){
            if(escaped[idx]) continue;

            int personR = R[idx];
            int personC = C[idx];

            int size = Math.max(Math.abs(exitR - personR), Math.abs(exitC - personC)) +1;
            if(size > bestSize) continue;

            int maxC = Math.max(personC, exitC);
            int maxR = Math.max(personR, exitR);

            int r1 = Math.max(1, maxR-size+1);
            int c1 = Math.max(1, maxC-size+1);
            int r2 = r1+size-1;
            int c2 = c1+size-1;

            if(size == bestSize){
                if(r1 < bestR){
                    bestR = r1;
                    bestC = c1;
                }else if(r1 == bestR && bestC > c1){
                    bestC = c1;
                }
                continue;
            }

            bestR = r1;
            bestC = c1;
            bestSize = size;
        }

        return new int[]{bestSize, bestR, bestR+bestSize-1, bestC, bestC+bestSize-1};
    }

    static void rotate(int[] info){
        int size = info[0];
        int r1 = info[1];
        int r2 = info[2];
        int c1 = info[3];
        int c2 = info[4];

        int[][] copied = new int[N+1][N+1];
        for(int i = r1; i <= r2; i++){
            for(int j = c1; j <= c2; j++){
                copied[i][j] = map[i][j];
            }
        }

        // 맵 회전
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                map[r1 + i][c1 + j] = (copied[r1 + size - 1 - j][c1 + i] > 0 
                ? copied[r1 + size - 1 - j][c1 + i]-1 : 0); 
            }
        }

        // 출구: 한 번만
        int er = exitR - r1, ec = exitC - c1;          // 로컬 좌표
        exitR = r1 + ec;
        exitC = c1 + size - 1 - er;

        // 사람: 각자 한 번만
        for(int idx = 1; idx <= M; idx++){
            if(R[idx] < r1 || R[idx] >= r1 + size || C[idx] < c1 || C[idx] >= c1 + size) continue;
            int pr = R[idx] - r1, pc = C[idx] - c1;
            R[idx] = r1 + pc;
            C[idx] = c1 + size - 1 - pr;
        }
    }

    static void move(){
        for(int i = 1; i <= M; i++){
            if(escaped[i]) continue; // 탈출한 사람은 볼 필요 없음.
            int r = R[i];
            int c = C[i];
            int criteria = Math.abs(r-exitR) + Math.abs(c-exitC);

            int minD = Integer.MAX_VALUE;
            int dir = -1;

            for(int d = 0; d < 4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];

                if(isOutside(nr, nc)) continue;
                if(map[nr][nc] > 0) continue;

                int distance = Math.abs(nr-exitR) + Math.abs(nc-exitC);
                if(criteria > distance && distance < minD){
                    minD = distance;
                    dir = d;
                }
            }

            if(dir == -1) continue;

            // 실제 움직임
            R[i] = r + dr[dir];
            C[i] = c + dc[dir];
            moved++;
            if(R[i] == exitR && C[i] == exitC){ // 출구를 찾은 사람
                    escaped[i] = true;
                    finished ++;
            }
        }
    }

    // static int[][] bfs(){
    //     int[][] distance = new int[N+1][N+1];
    //     for(int i = 1; i <= N; i++){
    //         Arrays.fill(distance[i], Integer.MAX_VALUE);
    //     }
    //     distance[exitR][exitC] = 0;

    //     Queue<int[]> q = new ArrayDeque<>();
    //     q.add(new int[]{exitR, exitC, 0});

    //     while(!q.isEmpty()){
    //         int[] cur = q.poll();

    //         for(int i = 0; i < 4; i++){
    //             int nr = cur[0] + dr[i];
    //             int nc = cur[1] + dc[i];

    //             if(isOutside(nr, nc)) continue;
    //             if(map[nr][nc] > 0) continue; // 장애물인 경우
    //             if(distance[nr][nc] <= cur[2]+1) continue;

    //             distance[nr][nc] = cur[2]+1;
    //             q.add(new int[]{nr, nc, cur[2]+1});
    //         }
    //     }

    //     return distance;
    // }

    static boolean isOutside(int r, int c){
        return r < 1 || r > N || c < 1 || c > N;
    }

    static void print(String title){
        if(!DEBUG) return;
        StringBuilder sb = new StringBuilder();
        sb.append("==" + title + "== \n");
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(i == exitR && j == exitC) sb.append("e ");
                else{sb.append(map[i][j]+ " ");}
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }

    static void printPeople(String title){
        if(!DEBUG) return;
        StringBuilder sb = new StringBuilder();
        sb.append("==" + title + "== \n");
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                boolean isPerson = false;
                for(int idx = 1; idx <= M; idx++){
                    if(R[idx] == i && C[idx] == j){
                        sb.append(idx);
                        isPerson = true;
                    }
                }
                if(!isPerson) sb.append(". ");
                else sb.append(" ");
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }
}