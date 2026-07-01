import java.io.*;
import java.util.*;

public class Main {
    static int[][] arr;
    static int N;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        arr = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = 0;

        for(int i =0; i < N; i++){
            for(int j =0 ; j < N; j++){
                int[][] burstArr = burstAndAdjust(i, j);

                max = Math.max(max, countPairs(burstArr));
            }
        }

        System.out.print(max);
    }

    static int countPairs(int[][] temp){
        int cnt = 0;

        for(int i =0; i < N; i++){
            for(int j =0 ; j < N; j++){
                if(temp[i][j] == 0) continue;
                if(j < N-1 && temp[i][j] == temp[i][j+1]) cnt++;
                if(i < N-1 && temp[i][j] == temp[i+1][j]) cnt++;
            }
        }
        return cnt;
    }

    static int[][] burstAndAdjust(int i, int j){
        int[][] cloned = new int[N][N];
        for(int k = 0 ; k < N; k++){
            cloned[k] = arr[k].clone();
        }

        int num = cloned[i][j];
        cloned[i][j] = 0;

        for(int n = 1; n < num; n++){
            for(int dir = 0; dir < 4; dir++){
                int ni = i + dx[dir] * n;
                int nj = j + dy[dir] * n;

                if(ni < 0 || ni >= N || nj < 0 || nj >= N) continue;

                cloned[ni][nj] = 0;
            }
        }

        for(int col = j-(num-1); col <= j+(num-1); col++){
            if(col < 0 || col >= N) continue;

            int idx = N-1;
            for(int row = N-1; row >= 0; row--){
                if(cloned[row][col] == 0) continue;

                cloned[idx--][col] = cloned[row][col];
            }

            for(int row = idx; row >=0; row--){
                cloned[row][col] = 0;
            }
        }

        return cloned;
    }

}