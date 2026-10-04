import java.io.*;
import java.util.*;

public class Main {
    static boolean DEBUG = false;
    static int N, M, K;
    static int[][] map;
    static int[] turtle; // turtle 상태
    static int[][] turtleMap; // turtle 위치 기록
    static int[] turtleR, turtleC;
    static int[][] volMap; // volcano 위치 기록
    static int[] volR, volC, volP, volCur;
    static boolean[] erupted; // 아직 분출하지 않은 화산 중 P 넘는 화산 터트리기 위함.
    static int turn; // 현재 턴 수
    static int finishedTur; // 화석이 되거나 안식처에 도착한 거북이 수
    static int[][] heat; // 열기
    static int[] dr = {0, 1, 0, -1}; // 우, 하, 좌, 상
    static int[] dc = {1, 0, -1, 0};
    static Queue<Integer> erupting;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 산호초 정보
        map = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 거북이 정보
        turtle = new int[M];
        turtleMap = new int[N][N];
        for(int i = 0; i < N; i++){
            Arrays.fill(turtleMap[i], -1);
        }
        turtleR = new int[M];
        turtleC = new int[M];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            turtleR[i] = r;
            turtleC[i] = c;
            turtleMap[r][c] = i;
        }

        // 화산 위치
        volMap = new int[N][N];
        for(int i = 0; i < N; i++){
            Arrays.fill(volMap[i], -1);
        }
        volR = new int[K];
        volC = new int[K];
        volP = new int[K];
        volCur = new int[K];
        erupted = new boolean[K];  
        heat = new int[N][N];  
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            volR[i] = r;
            volC[i] = c;
            volP[i] = p;
            volMap[r][c] = i;
        }

        print("입력 후");

        turn = 0;
        finishedTur = 0;
        while(turn++ < 100 && finishedTur < M){
            // 1. 바다거북 이동
            moveTurtles();
            print("이동 후");

            // 2. 화산 압력 증가
            increasePressure();

            // 3. 화산 분출 및 연쇄 반응
            erupt();
            fossilize();

            // 4. 환경 초기화
            init();
        }

        for(int i = 0; i < M; i++){
            System.out.println(turtle[i] == 0 ? -1 : turtle[i]);
        }
    }

    static void fossilize(){
        for(int i = 0; i < M; i++){
            if(turtle[i] != 0) continue;
            
            int r = turtleR[i];
            int c = turtleC[i];

            if(heat[r][c] >= 20){
                finishedTur++;
                turtle[i] = -1;
            }
        }
    }

    static void init(){
        heat = new int[N][N];
        for(int i = 0; i < K; i++){
            if(erupted[i]){
                volCur[i] = 0; // 마그마 압력 0으로 초기화
            }
        }
    }

    static void erupt(){
        Arrays.fill(erupted, false);
        erupting = new ArrayDeque<>();

        // 원래 터질려고 했던 화산들
        for(int i = 0; i < K; i++){
            if(volP[i] <= volCur[i]){
                erupting.add(i);
                erupted[i] = true;
            }
        }

        while(!erupting.isEmpty()){
            int v = erupting.poll();
            spreadHeat(v, -1);

            for(int d = 0; d < 4; d++){
                spreadHeat(v, d);
            }
        }
    }

    static void spreadHeat(int v, int dir){
        int p = volP[v];
        int r = volR[v];
        int c = volC[v];

        if(dir == -1){
            heat[r][c] += p;
            return;
        }

        while(true){
            r += dr[dir];
            c += dc[dir];
            p /= 2;

            if(isOutside(r, c)) break;
            if(p == 0 || map[r][c] == 1) break;

            heat[r][c] += p;

            if(volMap[r][c] != -1 && !erupted[volMap[r][c]] && heat[r][c] + volCur[volMap[r][c]] >= volP[volMap[r][c]]){
                erupted[volMap[r][c]] = true;
                erupting.add(volMap[r][c]);
            }
        }
    }

    static void moveTurtles(){
        for(int i = 0; i < M; i++){
            if(turtle[i] != 0) continue;
            int[][] dist = bfs(i);
            int dir = -1;
            int shortest = Integer.MAX_VALUE;
            // 4방향 중 가장 최단 거리 찾기
            for(int d = 0; d < 4; d++){
                int r = turtleR[i] + dr[d];
                int c = turtleC[i] + dc[d];

                if(isOutside(r, c)) continue;

                if(dist[r][c] < shortest){
                    dir = d;
                    shortest = dist[r][c];
                }
            }

            if(dir == -1) continue;

            // 기존 거북이 초기화
            turtleMap[turtleR[i]][turtleC[i]] = -1;
            
            int nextR = turtleR[i] + dr[dir];
            int nextC = turtleC[i] + dc[dir];
            turtleR[i] = nextR;
            turtleC[i] = nextC;
            
            // 안식처 도착
            if(nextR == N-1 && nextC == N-1){
                turtle[i] = turn;
                finishedTur++;
                continue;
            }

            turtleMap[nextR][nextC] = i;
        }
    }

    static int[][] bfs(int num){
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{N-1, N-1, 0});

        int[][] dist = new int[N][N];
        for(int i = 0; i < N; i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[N-1][N-1] = 0;

        while(!q.isEmpty()){
            int[] cur = q.poll();

            for(int d = 0; d < 4; d++){
                int nextR = cur[0] + dr[d];
                int nextC = cur[1] + dc[d];

                if(isOutside(nextR, nextC)) continue;
                if(map[nextR][nextC] == 1 || turtleMap[nextR][nextC] >= 0) continue;
                if(dist[nextR][nextC] <= cur[2] + 1) continue;

                dist[nextR][nextC] = cur[2] + 1;
                q.add(new int[]{nextR, nextC, cur[2]+1});
            }
        }

        return dist;
    }

    static void increasePressure(){
        for(int i = 0; i < K; i++){
            volCur[i] += 10;
        }
    }

    static boolean isOutside(int r, int c){
        return (r < 0 || c < 0 || r >= N || c >= N);
    }

    static void print(String title){
        if(!DEBUG) return;
        StringBuilder sb = new StringBuilder();
        sb.append("==" + title + "== turn :  " + turn).append("\n");
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] == 1) sb.append("# ");
                else if(turtleMap[i][j] != -1){
                    sb.append("T").append(turtleMap[i][j]).append(" ");
                }else if(volMap[i][j] != -1){
                    sb.append("H").append(volMap[i][j]).append(" ");
                }
                else sb.append(". ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}