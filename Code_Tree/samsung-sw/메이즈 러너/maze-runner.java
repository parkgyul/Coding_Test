import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] map;
    static int[] R, C;
    static boolean[] escaped;
    static int exitR, exitC;
    static int moved;
    static int finished;
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
        escaped = new boolean[M+1];
        for(int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            R[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        exitR = Integer.parseInt(st.nextToken());
        exitC = Integer.parseInt(st.nextToken());

        while(K-- > 0){
            move();
            if(finished >= M) break;

            int[] info = findRotate();
            rotate(info);
        }

        System.out.println(moved);
        System.out.println(exitR + " " + exitC);
    }
    static void rotate(int[] info){
        int r = info[0];
        int c = info[1];
        int size = info[2];

        int[][] temp = new int[size][size];

        // r,c ~ (r+size-1,c+size-1) 애들 복사 하기
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                temp[i][j] = map[r+i][c+j];
            }
        }
        
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                map[r+i][c+j] = temp[(size-1)-j][i];

                // 내구도 깎기
                if(map[r+i][c+j] > 0) map[r+i][c+j]--;
            }
        }

        int er = exitR - r, ec = exitC - c; // 기준 위치
        exitR = r + ec;
        exitC = c + (size-1)-er;

        for(int idx = 1; idx <= M; idx++){
            if(escaped[idx] || !isInside(R[idx], C[idx], r, c, size)) continue;

            int pr = R[idx] - r, pc = C[idx] - c;
            R[idx] = r + pc;
            C[idx] = c + (size-1) -pr;
        }
    }

    static int[] findRotate(){
        for(int size = 2; size <= N; size++){
            for(int i = 1; i+size-1 <= N; i++){
                for(int j = 1; j+size-1 <= N; j++){
                    // exit이 해당 사이즈 정사각형 안에 있는지 확인
                    if(!isInside(exitR, exitC, i, j, size)) continue;

                    // 있으면, 사람도 있는지 확인
                    for(int idx = 1; idx <= M; idx++){
                        if(!escaped[idx] && isInside(R[idx], C[idx], i, j, size)){
                            return new int[]{i, j, size}; // i, j를 시작점으로 한 변이 size인 정사각형
                        }
                    }
                }
            }
        }

        return null;
    }

    static boolean isInside(int cr, int cc, int r, int c, int size){
        return cr >= r && cr < r+size && cc >= c && cc < c+size;
    }

    static void move(){
        for(int idx = 1; idx <= M; idx++){
            if(escaped[idx]) continue; // 이미 탈출한 사람

            int r = R[idx];
            int c = C[idx];
            int bestD = Math.abs(r - exitR) + Math.abs(c - exitC);
            int dir = -1;

            for(int d = 0; d < 4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];

                if(isOutside(nr, nc)) continue;
                if(map[nr][nc] > 0) continue;

                int distance = Math.abs(nr - exitR) + Math.abs(nc - exitC);
                if(bestD <= distance) continue;

                bestD = distance;
                dir = d;
            }

            if(dir == -1) continue;

            R[idx] += dr[dir];
            C[idx] += dc[dir];
            moved++;

            if(R[idx] == exitR && C[idx] == exitC){
                finished++;
                escaped[idx] = true;
            }
        }
    }

    static boolean isOutside(int r, int c){
        return r < 1 || c < 1 || r > N || c > N;
    }
}