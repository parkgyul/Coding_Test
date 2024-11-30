import java.awt.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

class Main{
    static int n, findNum;
    static int[][] arr;
    static int[] di = {-1, 0, 1, 0}; //상우하좌
    static int[] dj = {0, 1, 0, -1};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        findNum = Integer.parseInt(br.readLine());
        arr = new int[n+1][n+1];

        snail();
        StringBuilder print = new StringBuilder();
        for(int i = 1; i< n+1; i++){
            for(int j = 1; j < n+1; j++ ){
                print.append(arr[i][j]+ " ");
            }
            print.append('\n');
        }

        System.out.print(print);
        System.out.println(sb);
    }

    public static void snail(){

        int move = 1;
        int current_i = n/2 + 1;
        int current_j = n/2 + 1;
        int num = 1;
        if(findNum == 1){
            sb.append(current_i +  " " +current_j);
        }
        arr[current_i][current_j] = num ++;
        int cnt = 0;
        int direction_cnt = 0;
        while(true){
            if(cnt == 2) {
                move++;
                cnt = 0;
            }
            for(int i = 0; i < move; i++){

                current_i += di[direction_cnt];
                current_j += dj[direction_cnt];
                if(num == findNum) {
                    sb.append(current_i +  " " +current_j);
                }
                arr[current_i][current_j] = num ++;
                if(num == (n*n +1)) return;
            }
            direction_cnt ++;
            if(direction_cnt == 4) direction_cnt = 0;
            cnt++;
        }
    }

}
