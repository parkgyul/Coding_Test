import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] building;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        building = new int[N][M];

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++){
                building[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(Q-- > 0){
            st = new StringTokenizer(br.readLine());

            int si = Integer.parseInt(st.nextToken())-1;
            int sj = Integer.parseInt(st.nextToken())-1;
            int ei = Integer.parseInt(st.nextToken())-1;
            int ej = Integer.parseInt(st.nextToken())-1;

            turnClock(si, sj, ei, ej);
            building = calculate(si, sj, ei, ej);
        }

        for(int i =0 ; i < N; i++){
            for(int j = 0; j < M; j++){
                System.out.print(building[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void turnClock(int si, int sj, int ei, int ej){
        int temp = building[si][sj];

        for(int i = si; i < ei; i++){
            building[i][sj] = building[i+1][sj];
        }

        for(int j = sj; j < ej; j++){
            building[ei][j] = building[ei][j+1];
        }
        
        for(int i = ei; i > si; i--){
            building[i][ej] = building[i-1][ej];
        }

        for(int j = ej; j > sj+1; j--){
            building[si][j] = building[si][j-1];
        }

        building[si][sj+1] = temp;
    }

    static int[][] calculate(int si, int sj, int ei, int ej){
        int[][] result = new int[N][M];
        for(int i = 0; i < N; i++){
            result[i] = building[i].clone();
        }

        for(int i = si; i <= ei; i++){
            for(int j = sj; j <= ej; j++){
                int sum = building[i][j];
                int cnt = 1;
                for(int dir = 0; dir < 4; dir++){
                    int ni = i + dx[dir];
                    int nj = j + dy[dir];

                    if(ni < 0 || nj < 0 || ni >= N || nj >= M) continue;
                    sum += building[ni][nj];
                    cnt++;
                }

                result[i][j] = sum/cnt;
            }
        }

        return result;
    }
}