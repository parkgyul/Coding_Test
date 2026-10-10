import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K; // NxM 격자, K번 반복
    static int broken;
    static int[][] map;
    static int[][] attack;
    static boolean[][] isAttacked;
    static int[] dr = {0, 1, 0, -1}; // 우, 하, 좌, 상
    static int[] dc = {1, 0, -1, 0};
    static int turn;
    static int[] tr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] tc = {-1, 0, 1, -1,  1, -1, 0, 1};
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        broken = 0;

        map = new int[N][M];
        attack = new int[N][M];
        isAttacked = new boolean[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) broken++;
            }
        }

        turn = 0;
        while(turn++ < K && broken < N*M-1){
            // 1. 공격자 선정
            int[] attacker = findAttacker();

            // 2. 공격당할 자 선정
            int[] defender = findDefender();
            
            // 3. 공격 & 포탑 무너짐
            goAttack(attacker, defender);
            // print("공격 후");

            // 4.  포탑 정비
            maintain();

        }

        System.out.print(getResult());
    }

    static void maintain(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] != 0 && !isAttacked[i][j] && attack[i][j] != turn){
                    map[i][j]++;
                }
            }
        }

        isAttacked = new boolean[N][M];
    }

    static void goAttack(int[] attacker, int[] defender){
        // 공격력 증가
        map[attacker[0]][attacker[1]] += (N+M);
        // 공격한 턴 저장
        attack[attacker[0]][attacker[1]] = turn;
        int power = map[attacker[0]][attacker[1]];

        // 1. 레이저 공격
        List<int[]> path = findPath(attacker, defender);
        if(path != null ){
            attackLaser(power, path, defender[0], defender[1]);
        }else{
            throwBomb(power, attacker[0], attacker[1], defender[0], defender[1]);
        }
    }

    static void throwBomb(int power, int ar, int ac, int r, int c){
        map[r][c] = Math.max(0, map[r][c]-(power));
        if(map[r][c]  <= 0) broken++;
        isAttacked[r][c] = true;

        for(int d = 0; d < 8; d++){
            // 가장자리 처리를 위함.
            int[] newRC = processEdge(r + tr[d], c + tc[d]);
            int nr = newRC[0];
            int nc = newRC[1];

            // 이미 무너진 포탑은 건너뜀
            if(map[nr][nc] == 0) continue;
            if(nr == ar && nc == ac) continue;
            map[nr][nc] = Math.max(0, map[nr][nc]-(power/2));
            if(map[nr][nc]  <= 0) broken++;
            isAttacked[nr][nc] = true;
        }
    }

    static void print(String title){
        StringBuilder sb = new StringBuilder();
        sb.append("==" + title + "==\n");
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void attackLaser(int power, List<int[]> path, int defR, int defC){
        for(int[] p : path){
            if(p[0] == defR && p[1] == defC) continue;
            map[p[0]][p[1]] = Math.max(0, map[p[0]][p[1]]-(power/2));
            if(map[p[0]][p[1]]  <= 0) broken++;
            isAttacked[p[0]][p[1]] = true;
        }

        map[defR][defC] = Math.max(0, map[defR][defC]-power);
        if(map[defR][defC] <= 0) broken++;
        isAttacked[defR][defC] = true;
    }

    static List<int[]> findPath(int[] start, int[] dest){
        int[][] prevR = new int[N][M];
        int[][] prevC = new int[N][M];

        for(int i = 0; i < N; i++){
            Arrays.fill(prevR[i], -1);
            Arrays.fill(prevC[i], -1);
        }

        int sr = start[0];
        int sc = start[1];
        prevR[sr][sc] = Integer.MAX_VALUE;
        prevC[sr][sc] = Integer.MAX_VALUE;

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sr, sc});

        boolean found = false;
        while(!q.isEmpty()){
            int[] cur = q.poll();

            if(cur[0] == dest[0] && cur[1] == dest[1]){
                found = true;
                break;
            }

            for(int d = 0; d < 4; d++){
                // 가장자리 처리를 위함.
                int[] newRC = processEdge(cur[0] + dr[d], cur[1] + dc[d]);
                int nr = newRC[0];
                int nc = newRC[1];

                // 이미 무너진 포탑 지날 수 없음.
                if(map[nr][nc] == 0) continue;
                if(prevR[nr][nc] != -1) continue;

                prevR[nr][nc] = cur[0];
                prevC[nr][nc] = cur[1];
                q.add(new int[]{nr, nc});
            }
        }

        if(found){
            List<int[]> path = new ArrayList<>();
            int r = prevR[dest[0]][dest[1]];
            int c = prevC[dest[0]][dest[1]];

            while(!(r == sr && c == sc)){
                path.add(new int[]{r, c});
                int pr = prevR[r][c];
                int pc = prevC[r][c];
                r = pr;
                c = pc;
            }
            return path;
        }else{
            return null;
        }
    }

    static int[] processEdge(int nr, int nc){
        return new int[]{(nr + N) % N, (nc + M) % M};
    }

    static int[] findAttacker(){
        int power = Integer.MAX_VALUE;
        int attackTurn = Integer.MIN_VALUE;
        int sum = Integer.MIN_VALUE;
        int col = Integer.MIN_VALUE;
        int row = -1;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 0) continue;
                if(map[i][j] < power){
                    power = map[i][j];
                    attackTurn = attack[i][j];
                    sum = i+j;
                    row = i;
                    col = j;
                }else if(map[i][j] == power){
                    if(attackTurn < attack[i][j]){
                        attackTurn = attack[i][j];
                        sum = i+j;
                        row = i;
                        col = j;
                    }else if(attackTurn == attack[i][j]){
                        if(sum < i+j){
                            sum = i+j;
                            row = i;
                            col = j;
                        }else if(sum == i+j && j > col){
                            row = i;
                            col = j;
                        }
                    }
                }
            }
        }

        return new int[]{row, col};
    }

    static int[] findDefender(){
        int power = Integer.MIN_VALUE;
        int attackTurn = Integer.MAX_VALUE;
        int sum = Integer.MAX_VALUE;
        int col = Integer.MAX_VALUE;
        int row = -1;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 0) continue;
                if(map[i][j] > power){
                    power = map[i][j];
                    attackTurn = attack[i][j];
                    sum = i+j;
                    row = i;
                    col = j;
                }else if(map[i][j] == power){
                    if(attackTurn > attack[i][j]){
                        attackTurn = attack[i][j];
                        sum = i+j;
                        row = i;
                        col = j;
                    }else if(attackTurn == attack[i][j]){
                        if(sum > i+j){
                            sum = i+j;
                            row = i;
                            col = j;
                        }else if(sum == i+j && j < col){
                            row = i;
                            col = j;
                        }
                    }
                }
            }
        }

        return new int[]{row, col};
    }

    static int getResult(){
        int max = -1;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(max < map[i][j]){
                    max = map[i][j];
                }
            }
        }

        return max;
    }
}