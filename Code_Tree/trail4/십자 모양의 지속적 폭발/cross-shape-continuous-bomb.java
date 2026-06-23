import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] arr;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];

        for(int i =0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0 ; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(M-- > 0){
            int col = Integer.parseInt(br.readLine())-1;

            int num = burst(col);
            if(num != -1) adjustGravity(num, col);
        }

        
        for(int i =0 ; i < N; i++){
            for(int j =0 ; j < N; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    static int burst(int col){
        int num = -1;
        int topI = -1;
        for(int i = 0; i < N; i++){
            if(arr[i][col] != 0){
                num = arr[i][col];
                topI = i;
                break;
            }
        }

        if(num == -1) return -1;

        arr[topI][col] = 0;

        for(int dir = 0; dir < 4; dir++){
            for(int i = 1; i < num; i++){
                int ni = topI + dx[dir] * i;
                int nj = col + dy[dir] * i;

                if(ni < 0 || ni >= N || nj < 0 || nj >= N) continue;

                arr[ni][nj] = 0;
            }
        }

        return num;
    }

    static void adjustGravity(int num, int col){

        for(int j = col - num +1; j <= col+ num -1; j++){
            if(j < 0 || j >= N) continue;

            int index = N-1;
            for(int i = N-1; i >= 0; i--){
                if(arr[i][j] == 0) continue;

                arr[index--][j] = arr[i][j];
            }

            for(int i = index; i >= 0; i--){
                arr[i][j] = 0;
            }
        }
        
    }
}