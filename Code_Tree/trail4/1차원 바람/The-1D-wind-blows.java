import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] arr;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for(int i =0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(Q-- > 0){
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken())-1;
            char ch = st.nextToken().charAt(0);

            if(ch == 'L') left(row);
            else right(row);

            int up = row-1;
            int down = row+1;

            while(true){
                if(up >= 0){
                    if(!check(up, up+1)){
                        if(ch == 'L') right(up);
                        else left(up);
                    }else{ // 전파 끝
                        up = -1;
                    }
                }

                if(down < N){
                    if(!check(down, down-1)){
                        if(ch == 'L') right(down);
                        else left(down);
                    }else{
                        down = N;
                    }
                }

                up--;
                down++;
                ch = (ch == 'L' ? 'R' : 'L');
                if(up < 0 && down >= N) break;
            }    
        }

        for(int i =0 ; i < N; i++){
            for(int j = 0; j < M; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

    }

    static boolean check(int r1, int r2){
        for(int j = 0; j < M; j++){
            if(arr[r1][j] == arr[r2][j]) return false;
        }

        return true;
    }

    static void left(int row){ // 왼쪽에서 바람 불어옴
        int temp = arr[row][M-1];
        for(int j = M-1 ; j >= 1; j--){
            arr[row][j] = arr[row][j-1];
        }

        arr[row][0] = temp;
    }

    static void right(int row){
        int temp = arr[row][0];
        for(int j = 1 ; j < M; j++){
            arr[row][j-1] = arr[row][j];
        }

        arr[row][M-1] = temp;
    }

}