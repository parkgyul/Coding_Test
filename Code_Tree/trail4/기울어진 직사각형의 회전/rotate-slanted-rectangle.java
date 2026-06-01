import java.io.*;
import java.util.*;

public class Main {
    static int[][] arr;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        arr = new int[N][N];

        StringTokenizer st;
        for(int i =0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j =0 ; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken())-1;
        int c = Integer.parseInt(st.nextToken())-1;
        int m1 = Integer.parseInt(st.nextToken());
        int m2 = Integer.parseInt(st.nextToken());
        int m3 = Integer.parseInt(st.nextToken());
        int m4 = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

        if(dir == 0){ // 반시계 방향
            rotate1(r, c, m1, m2);
        }else{ // 시계 방향
            rotate2(r, c, m1, m2);
        }

         for(int i =0; i < N; i++){
            for(int j =0 ; j < N; j++){
                System.out.print(arr[i][j] + " ");
            }

            System.out.println();
        }
    }

    static void rotate1(int r, int c, int m1, int m2){
        int temp = arr[r][c];

        for(int cnt = 0; cnt < m2; cnt++){
            arr[r][c] = arr[r-1][c-1];
            r = r-1;
            c = c-1;
        }

        for(int cnt = 0; cnt < m1; cnt++){
            arr[r][c] = arr[r-1][c+1];
            r = r-1;
            c = c+1;
        }

        for(int cnt = 0; cnt < m2; cnt++){
            arr[r][c] = arr[r+1][c+1];
            r = r+1;
            c = c+1;
        }

        for(int cnt = 0; cnt < m1; cnt++){
            arr[r][c] = arr[r+1][c-1];
            r = r+1;
            c = c-1;
        }

        arr[r-1][c+1] = temp;
    }

    static void rotate2(int r, int c, int m1, int m2){
        int temp = arr[r][c];

        for(int cnt = 0; cnt < m1; cnt++){
            arr[r][c] = arr[r-1][c+1];
            r = r-1;
            c = c+1;
        }

        for(int cnt = 0; cnt < m2; cnt++){
            arr[r][c] = arr[r-1][c-1];
            r = r-1;
            c = c-1;
        }

        for(int cnt = 0; cnt < m1; cnt++){
            arr[r][c] = arr[r+1][c-1];
            r = r+1;
            c = c-1;
        }

        for(int cnt = 0; cnt < m2; cnt++){
            arr[r][c] = arr[r+1][c+1];
            r = r+1;
            c = c+1;
        }

        arr[r-1][c-1] = temp;
    }
}