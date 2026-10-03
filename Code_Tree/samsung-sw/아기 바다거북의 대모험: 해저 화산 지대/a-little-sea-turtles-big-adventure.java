import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K, turn;
    static int[][] map; // 산호초
    static int[][] tur; // 거북이
    static int[][] vol; // 화산
    static int[][] heat; // 열기
    static int[] turtle; // 거북이 살아있나? (0 : yes , -1 : 화석, 1보다 크면 도착한)
    static int[] turtleR, turtleC; // 거북이 현재 위치
    static int[] volR, volC, volP, volCur; // 화산 r, 화산 c, 화산 임계치, 화산 현재 마그마
    static boolean[] erupted; // 이번 턴 분출 여부
    static int finishedTur;
    static int[] dr = {0, 1, 0, -1}; // 우, 하, 좌, 상
    static int[] dc = {1, 0, -1, 0};
    static Queue<Integer> eruptedVol;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 격자 크기
        M = Integer.parseInt(st.nextToken()); // 거북이 마리 수
        K = Integer.parseInt(st.nextToken()); // 화산 개수

        map = new int[N][N];
        tur = new int[N][N];
        vol = new int[N][N];

        turtle = new int[M];
        turtleR = new int[M];
        turtleC = new int[M];

        volR = new int[K];
        volC = new int[K];
        volP = new int[K];
        volCur = new int[K];

        erupted = new boolean[K];
        heat = new int[N][N];

        // 지도 정보 (산호초)
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 거북이 위치 정보
        for(int i = 0; i < N; i++){
            Arrays.fill(tur[i], -1);
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            turtleR[i] = r;
            turtleC[i] = c;
            tur[r][c] = i;
        }

        // 화산 정보
        for(int i = 0; i < N; i++){
            Arrays.fill(vol[i], -1);
        }
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            volR[i] = r;
            volC[i] = c;
            volP[i] = p;
            vol[r][c] = i;
        }

        turn = 0;
        finishedTur = 0;
        while(turn++ < 100 && finishedTur < M){
            // 1. 바다 거북 이동
            moveTurtle();

            // 2. 화산 압력 증가
            increasePressure();

            // 3. 화산 분출 및 연쇄 반응
            burst();
            makeStone();

            // 4. 환경 초기화
            initialize();
        }

        for(int i = 0; i < M; i++){
            System.out.println(turtle[i] == 0 ? -1 : turtle[i]);
        }
    }

    static void moveTurtle(){
        for(int i = 0; i < M; i++){
            if(turtle[i] != 0) continue; // 움직일 필요가 없는 거북이들

            int[][] dist = bfs(i); // 최단 거리 찾기
            int dir = -1; // 거북이가 갈 방향
            int shortest = Integer.MAX_VALUE; // 최단 거리
            //현재 거북이 위치
            int r = turtleR[i];
            int c = turtleC[i];

            // 가장 최단 거리 방향 찾기
            for(int d = 0; d < 4; d++){
                int nextR = turtleR[i] + dr[d];
                int nextC = turtleC[i] + dc[d];

                if(isOutside(nextR, nextC)) continue;
                if(shortest <= dist[nextR][nextC]) continue;

                shortest = dist[nextR][nextC];
                dir = d;
            }
            
            // 최단 거리가 없을때는 
            if(dir == -1) continue;

            tur[r][c] = -1; // 기존 거북이 위치 없애기

            int nextR = r + dr[dir];
            int nextC = c + dc[dir];

            // 안식처 도착 거북이
            if(nextR == N-1 && nextC == N-1){
                turtle[i] = turn;
                turtleR[i] = N-1;
                turtleC[i] = N-1;
                finishedTur++;
                continue;
            }

            // 거북이 위치 옮기기
            turtleR[i] = nextR;
            turtleC[i] = nextC;
            tur[nextR][nextC] = i;
        }
    }

    static int[][] bfs(int turtleNum){
        int[][] dist = new int[N][N];
        for(int i = 0; i < N; i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        dist[N-1][N-1] = 0;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{N-1, N-1, 0});

        while(!q.isEmpty()){
            int[] cur = q.poll();

            for(int i = 0; i < 4; i++){
                int nextR = cur[0] + dr[i];
                int nextC = cur[1] + dc[i];
                if(isOutside(nextR, nextC)) continue; // 범위 밖
                if(map[nextR][nextC] == 1 || tur[nextR][nextC] >= 0) continue; // 산호초 or 거북이 위치
                if(dist[nextR][nextC] <= cur[2] + 1) continue; // 최단 경로가 아닐라면

                dist[nextR][nextC] = cur[2] + 1;
                q.add(new int[]{nextR, nextC, cur[2] + 1});
            }
        }

        return dist;
    }

    static boolean isOutside(int r, int c){
        if(r < 0 || c < 0 || r >= N || c >= N) return true;
        return false;
    }

    static void increasePressure(){
        for(int i = 0; i < K; i++){
            volCur[i] += 10;
        }
    }

    static void burst(){
        eruptedVol = new LinkedList<>();
        Arrays.fill(erupted, false);

        // 처음에 분출될 화산들 찾기
        for(int i = 0; i < K; i++){
            if(volP[i] <= volCur[i]){
                eruptedVol.add(i);
                erupted[i] = true;
            }
        }

        while(!eruptedVol.isEmpty()){
            int i = eruptedVol.poll();
            int r = volR[i];
            int c = volC[i];
            int p = volP[i];
            
            addHeat(r, c, p);

            for(int d = 0; d < 4; d++){
                int nextR = r;
                int nextC = c;
                int nextH = p;
                while(true){
                    nextR += dr[d];
                    nextC += dc[d];
                    nextH /= 2;
                    if(isOutside(nextR, nextC) || nextH <= 0) break; // 범위 벗어나면
                    if(map[nextR][nextC] == 1) break; // 산호초 있으면?

                    addHeat(nextR, nextC, nextH);
                }
            }
        }
    }

    static void addHeat(int r, int c, int h){
        heat[r][c] += h;
        int v = vol[r][c]; // 화산

        // 현재 마그마 압력 + 해당 칸에 누적된 외부 열기 >= 분출 임계치 : 화산 분출
        if(v >= 0 && !erupted[v] && volCur[v] + heat[r][c] >= volP[v]){
            erupted[v] = true;
            eruptedVol.add(v);
        }
    }

    // 거북이 화석 만들기
    static void makeStone(){
        for(int i = 0; i < M; i++){
            if(turtle[i] != 0) continue; // 이미 안식처로 들어간 거북이 & 화석이 된 거북이는 패쓰.
            if(heat[turtleR[i]][turtleC[i]] >= 20){
                turtle[i] = -1; // 화석으로 만들기
                finishedTur++;
            }
        }
    }

    static void initialize(){
        heat = new int[N][N]; // 모든 열기 정보 초기화
        for(int i = 0; i < K; i++){
            if(!erupted[i]) continue;

            volCur[i] = 0;
        }
    }
}