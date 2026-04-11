import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.BufferedReader;

public class Main{
    static int R, C, M;
    static Shark[][] map; // 상어 크기 기록
    static int[] sharkR, sharkC, sharkS, sharkD;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new Shark[R+1][C+1];

        for(int i =1; i < R+1; i++){
            for(int j = 1; j < C+1; j++){
                map[i][j] = new Shark(0, 0);
            }
        }

        sharkR = new int[M+1]; // 상어 행
        sharkC = new int[M+1]; // 상어 열
        sharkS = new int[M+1]; // 상어 속력
        sharkD = new int[M+1]; // 상어 방향

        for(int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken()); // 속력
            int d = Integer.parseInt(st.nextToken()); // 방향
            int z = Integer.parseInt(st.nextToken()); // 크기

            map[r][c] = new Shark(i, z);

            sharkR[i] = r;
            sharkC[i] = c;
            sharkS[i] = s;
            sharkD[i] = d;
        }

        int cnt = 0;
        int result = 0;
        while(++cnt <= C){ // 1. 낚시왕 오른쪽 한 칸 이동
            // 2. 상어 잡기
            result += catchShark(cnt);

            // 3. 상어 이동
            moveShark();
        }

        System.out.println(result);
    }

    private static void moveShark(){
        Shark[][] newMap = new Shark[R+1][C+1];
        int[] newSharkR = new int[M+1];
        int[] newSharkC = new int[M+1];

        for(int i = 1; i <= R; i++){
            for(int j = 1; j <= C; j++){
                newMap[i][j] = new Shark(0, 0);
            }
        }

        for(int i = 1; i <= R; i++){
            for(int j = 1; j <= C; j++){
                int sharkNum = map[i][j].num;
                int sharkSize = map[i][j].size;
                if(sharkNum <= 0) continue;

                int ni = i;
                int nj = j;
                int dir = sharkD[sharkNum];
                int speed = sharkS[sharkNum];

                if(dir == 1 || dir == 2){
                    speed %= 2 * (R-1);

                    while(speed-- > 0){
                        if(dir == 1 && ni == 1) dir = 2;
                        else if(dir == 2 && ni == R) dir = 1;

                        if(dir == 1) ni--;
                        else ni++;
                    }
                }else{
                    speed %= 2 * (C-1);

                    while(speed-- > 0){
                        if(dir == 3 && nj == C) dir = 4;
                        else if (dir == 4 && nj == 1) dir = 3;

                        if(dir == 3) nj++;
                        else nj--;
                    }
                }

                sharkD[sharkNum] = dir;

                if(newMap[ni][nj].size > sharkSize) continue;

                newMap[ni][nj] = new Shark(sharkNum, sharkSize);
                newSharkR[sharkNum] = ni;
                newSharkC[sharkNum] = nj;
            }
        }

        sharkR = newSharkR;
        sharkC = newSharkC;
        map = newMap;
    }

    private static int catchShark(int col){
        for(int i = 1; i <= R; i++){
            int num = map[i][col].num;
            if(num >= 1){
                int size = map[i][col].size;
                map[i][col] = new Shark(0,0); // 상어 잡음
                return size;
            }
        }

        return 0;
    }

    public static class Shark{
        int num, size;

        public Shark(int num, int size){
            this.num = num;
            this.size = size;
        }
    }
}