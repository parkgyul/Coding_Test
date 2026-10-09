import java.io.*;
import java.util.*;

public class Main {
    static int N, M, P, C, D;
    static int RR, RC;
    static int[] SR, SC;
    static int[] STurn, SScore;
    static boolean[] SOver;
    static int[][] map;
    static int turn;
    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int finished;
    static int[] sdr = {-1, 0, 1, 0};
    static int[] sdc = {0, 1, 0, -1};
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken()); // 루돌프의 힘
        D = Integer.parseInt(st.nextToken()); // 산타의 힘

        st = new StringTokenizer(br.readLine());
        RR = Integer.parseInt(st.nextToken());
        RC = Integer.parseInt(st.nextToken());
        
        map = new int[N+1][N+1]; // 산타 위치 기록
        SR = new int[P+1];
        SC = new int[P+1];
        STurn = new int[P+1];
        SOver = new boolean[P+1];
        SScore = new int[P+1];
        for(int i = 1; i <= P; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            SR[num] = r;
            SC[num] = c;
            map[r][c] = num;
        }

        turn = 0;
        // print("INIT");
        finished = 0;
        while(turn++ < M){
            moveR(); // 루돌프의 움직임
            // print("루돌프 움직임");
            if(finished >= P) break;
            moveS();
            if(finished >= P) break;
            // print("산타 움직임");
            giveScore();
        }

        StringBuilder result = new StringBuilder();
        for(int i = 1; i <= P; i++){
            result.append(SScore[i]+ " ");
        }
        System.out.print(result);
    }

    static void giveScore(){
        for(int i = 1; i <= P; i++){
            if(SOver[i]) continue;
            SScore[i] += 1;
        }
    }

    static void print(String title){
        StringBuilder sb = new StringBuilder();
        sb.append("==" + title + " - turn :" + turn + "==\n");
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(RR == i && RC == j){
                     sb.append("R ");
                     continue;
                }
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void moveS(){
        for(int i = 1; i <= P; i++){
            // 기절했거나 이미 게임에서 탈락한 산타는 움직일 수 없습니다.
            if(SOver[i] || turn < STurn[i]) continue;

            // 현재 산타 위치
            int sr = SR[i]; 
            int sc = SC[i];

            int bestDir = -1;
            int bestDistance = (int)Math.pow((sr-RR), 2) + (int)Math.pow((sc-RC), 2);
            for(int d = 0; d < 4; d++){
                int nr = sr + sdr[d];
                int nc = sc + sdc[d];

                if(isOutside(nr, nc)) continue;
                // 다른 산타가 위치해있음.
                if(map[nr][nc] > 0) continue;

                int distance = (int)Math.pow((nr-RR), 2) + (int)Math.pow((nc-RC), 2);
                if(bestDistance > distance){
                    bestDistance = distance;
                    bestDir = d;
                }
            }

            if(bestDir == -1) continue;

            map[sr][sc] = 0;
            SR[i] += sdr[bestDir];
            SC[i] += sdc[bestDir];
            map[SR[i]][SC[i]] = i;

            SRush(i, bestDir);
        }
    }

    static void SRush(int santa, int dir){
        // 산타가 루돌프를 박음.
        if(RR == SR[santa] && RC == SC[santa]){
            // 반대 방향
            dir = (dir+2)%4;

            // 산타가 움직여서 충돌이 일어난 경우, 해당 산타는 D만큼의 점수를 얻게 됩니다.
            SScore[santa] += D;
            map[SR[santa]][SC[santa]] = 0;
            SR[santa] += (D*sdr[dir]);
            SC[santa] += (D*sdc[dir]);
            STurn[santa] = turn+2; // 기절에서 깨어나는 턴 저장

            // 산타가 밖으로 밀려남.
            if(isOutside(SR[santa], SC[santa])){
                SOver[santa] = true;
                finished ++;
            }else{
                if(map[SR[santa]][SC[santa]] != 0){ // 상호 작용 일어남.
                    inter(map[SR[santa]][SC[santa]], dir, false); // 밀려난 산타
                }
                map[SR[santa]][SC[santa]] = santa;
            }

        }
    }

    static void inter(int santa, int dir, boolean isRRush){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(santa);
        while(!q.isEmpty()){
            int cur = q.poll();

            SR[cur] += (isRRush ? dr[dir] : sdr[dir]);
            SC[cur] += (isRRush ? dc[dir] : sdc[dir]);

            if(isOutside(SR[cur], SC[cur])){
                SOver[cur] = true; // 밖으로 밀려남.
                finished++;
                continue;
            }

            if(map[SR[cur]][SC[cur]] > 0){ // 다른 산타랑 부딪힘
                q.add(map[SR[cur]][SC[cur]]);
            }

            map[SR[cur]][SC[cur]] = cur;
        }
    }

    static void moveR(){
        // 가장 가까운 산타 구하기
        int minD = Integer.MAX_VALUE;
        int maxR = Integer.MIN_VALUE;
        int maxC = Integer.MIN_VALUE;
        int santa = -1;
        for(int i = 1; i <= P; i++){
            if(SOver[i]) continue;
            int sr = SR[i];
            int sc = SC[i];

            int distance = (int)Math.pow((sr-RR), 2) + (int)Math.pow((sc-RC), 2);

            if(distance < minD){
                minD = distance;
                maxR = sr;
                maxC = sc;
                santa = i;
            }else if(distance == minD){
                if(maxR < sr){
                    maxR = sr;
                    maxC = sc;
                    santa = i;
                }else if(maxR == sr && maxC < sc){
                    maxC = sc;
                    santa = i;
                }
            }
        }

        int bestD = -1;
        int bestDistance = Integer.MAX_VALUE;
        for(int d = 0; d < 8; d++){
            int nr = RR + dr[d];
            int nc = RC + dc[d];

            if(isOutside(nr, nc)) continue;
            // 루돌프랑 산타 거리 가장 작은거 구하기
            int distance = (int)Math.pow((nr-maxR), 2) + (int)Math.pow((nc-maxC), 2);
            if(bestDistance > distance){
                bestD = d;
                bestDistance = distance;
            }
        }

        // 루돌프 이동 완료
        RR += dr[bestD];
        RC += dc[bestD];

        // 루돌프 박는지 확인
        RRush(santa, bestD);
    }

    static void RRush(int santa, int dir){
        // 루돌프가 돌진해서 산타를 박으면?
        if(RR == SR[santa] && RC == SC[santa]){
            // 루돌프가 움직여서 충돌이 일어난 경우, 해당 산타는 C만큼의 점수를 얻게 됩니다.
            SScore[santa] += C;
            // 이와 동시에 산타는 루돌프가 이동해온 방향으로 C 칸 만큼 밀려나게 됩니다.
            map[SR[santa]][SC[santa]] = 0; // 원래 위치 산타 초기화
            SR[santa] += (C*dr[dir]);
            SC[santa] += (C*dc[dir]);
            STurn[santa] = turn+2; // 기절에서 깨어나는 턴 저장

            // 산타가 밖으로 밀려남.
            if(isOutside(SR[santa], SC[santa])){
                SOver[santa] = true;
                finished++;
            }else{
                if(map[SR[santa]][SC[santa]] != 0){ // 상호 작용 일어남.
                    inter(map[SR[santa]][SC[santa]], dir, true); // 밀려난 산타
                }
                map[SR[santa]][SC[santa]] = santa;
            }
        }
    }

    static boolean isOutside(int r, int c){
        return (r < 1 || r > N || c < 1 || c > N);
    }
}