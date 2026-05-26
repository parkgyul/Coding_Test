import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][N];
        for(int i =0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        for(int i = 0; i < N; i++){
            int cnt = 1;
            int temp = arr[i][0];
            for(int j = 1; j < N; j++){
                if(temp == arr[i][j]){
                    cnt++;
                }else{
                    temp = arr[i][j];
                    cnt = 1;
                }
                if(cnt >= M){
                    answer++; 
                    break;
                }
            }
        }

        for(int j = 0; j < N; j++){
            int cnt = 1;
            int temp = arr[0][j];
            for(int i = 1; i < N; i++){
                if(temp == arr[i][j]){
                    cnt++;
                }else{
                    temp = arr[i][j];
                    cnt = 1;
                }
                if(cnt >= M){
                    answer++; 
                    break;
                }
            }
        }

        if(N == 1 && M == 1) answer+= 2;

        System.out.print(answer);
    }
}