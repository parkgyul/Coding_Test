import java.io.*;
import java.util.*;

public class Main {
    static boolean DEBUG = false;
    static int N, K, L;
    static int[][] map; // 먼지 양 저장~
    static int[][] robot; // 로봇 정보
    static int[] robotR;
    static int[] robotC;
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};

    static int[] sr = {0, -1, 0, 1};
    static int[] sc = {-1, 0, 1, 0};
    static StringBuilder result;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        // 먼지양 및 장애물 정보 입력
        map = new int[N+1][N+1];
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 로봇 정보 입력
        robot = new int[N+1][N+1];
        for(int i = 1; i <= N; i++){
            Arrays.fill(robot[i], -1);
        }
        robotR = new int[K];
        robotC = new int[K];
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            robot[r][c] = i;
            robotR[i] = r;
            robotC[i] = c;
        }

        result = new StringBuilder();

        print("INIT");
        while(L-- > 0){
            // 1. 청소기 이동
            moveRobots();
            // print("이동");

            // 2. 청소
            sweep();

            // 3. 먼지 축적
            putDust();
            // print("먼지");

            // 4. 먼지 확산
            spreadDust();
            print("먼지 확산");

            // 5. 출력
            getDustSum();
        }

        System.out.print(result);
    }

    static void getDustSum(){
        int sum = 0;
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(map[i][j] <= 0) continue;
                sum += map[i][j];
            }
        }

        result.append(sum).append("\n");
    }

    static void spreadDust(){
        int[][] newDust = new int[N+1][N+1];

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(map[i][j] != 0) continue;

                int sum = 0;
                for(int d = 0; d < 4; d++){
                    int nr = i + sr[d];
                    int nc = j + sc[d];

                    if(isOutside(nr, nc)) continue;
                    if(map[nr][nc] == -1) continue;
                    sum += map[nr][nc];
                } 

                newDust[i][j] += (sum/10);
            }
        }

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                map[i][j] += newDust[i][j];
            }
        }
    }

    static void putDust(){
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(map[i][j] > 0) map[i][j] += 5;
            }
        }
    }

    static void sweep(){
        for(int i = 0; i < K; i++){
            int r = robotR[i];
            int c = robotC[i];
            int dustSum = 0;
            int maxSum = -1;
            int maxD = -1;

            for(int d = 0; d < 4; d++){
                int nr = r + sr[d];
                int nc = c + sc[d];

                if(isOutside(nr, nc)|| map[nr][nc] == -1) continue;
                dustSum += Math.min(20, map[nr][nc]);
            }

            for(int d = 0; d < 4; d++){
                int nr = r + sr[d];
                int nc = c + sc[d];

                int excluded = 0;
                if(!isOutside(nr, nc) && map[nr][nc] != -1){
                    excluded = Math.min(20, map[nr][nc]);
                }

                if(maxSum < dustSum - excluded){
                    maxSum = dustSum - excluded;
                    maxD = d;
                }
            }

            map[r][c] = Math.max(map[r][c] - 20, 0);
            for(int d = 0; d < 4; d++){
                if(d == maxD) continue;
                int nr = r + sr[d];
                int nc = c + sc[d];

                if(isOutside(nr, nc)|| map[nr][nc] == -1) continue;
                map[nr][nc] = Math.max(map[nr][nc] - 20, 0); 
            }
        }
    }

    static void moveRobots(){
        for(int i = 0; i < K; i++){
            // 이미 격자에 먼지가 있으면 stay
            if(map[robotR[i]][robotC[i]] > 0) continue;
            int[] closest = findClosest(i);

            if(closest[0] == Integer.MAX_VALUE) continue;

            robot[robotR[i]][robotC[i]] = -1; // 기존 자리 로봇 없앰.
            robotR[i] = closest[0];
            robotC[i] = closest[1];
            robot[robotR[i]][robotC[i]] = i; // 로봇 이동
        }
    }

    static int[] findClosest(int num){
        boolean[][] visited = new boolean[N+1][N+1];
        int r = robotR[num];
        int c = robotC[num];
        visited[r][c] = true;

        int best = Integer.MAX_VALUE;
        int bestR = Integer.MAX_VALUE;
        int bestC = Integer.MAX_VALUE;

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r, c, 0});

        while(!q.isEmpty()){
            int[] cur = q.poll();

            if(best < cur[2]) continue;

            if(map[cur[0]][cur[1]] > 0){
                if(cur[2] < best || cur[0] < bestR || (cur[0] == bestR && cur[1] < bestC)){
                    best = cur[2];
                    bestR = cur[0];
                    bestC = cur[1];
                }
            }

            for(int i = 0; i < 4; i++){
                int nextR = cur[0] + dr[i];
                int nextC = cur[1] + dc[i];

                if(isOutside(nextR, nextC)) continue;
                // 이미 방문한 곳 or 물건이 있는 곳 or 청소기가 있는 곳
                if(visited[nextR][nextC] || map[nextR][nextC] == -1 || robot[nextR][nextC] != -1) continue;

                if(best < cur[2] + 1) continue;

                visited[nextR][nextC] = true;
                q.add(new int[]{nextR, nextC, cur[2]+1});
            }
        }

        return new int[]{bestR, bestC};
    }

    static boolean isOutside(int r, int c){
        return (r <= 0 || r > N || c <= 0 || c > N);
    }

    static void print(String title){
        if(!DEBUG) return;
        System.out.print("==L : " + L + " - "  + title + "==\n");
        StringBuilder mapInfo = new StringBuilder();
        StringBuilder robotInfo = new StringBuilder();

        mapInfo.append("---map---\n");
        robotInfo.append("---robot---\n");

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(robot[i][j] != -1){
                    robotInfo.append("R" + robot[i][j] + " ");
                }else{
                    robotInfo.append(".  ");
                }

                mapInfo.append(map[i][j] + "   ");
            }
            robotInfo.append("\n");
            mapInfo.append("\n");
        }

        System.out.print(mapInfo);
        System.out.print(robotInfo);
    }
}