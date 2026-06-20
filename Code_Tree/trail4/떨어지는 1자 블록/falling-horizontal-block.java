import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken())-1;

        int[][] arr = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int maxI = 0;
        for(int i = 0; i < N; i++){
            boolean flag = true;
            for(int j = K; j < K+M; j++){
                if(arr[i][j] == 1){
                    flag = false;
                    break;
                }
            }
            if(!flag) break;
            else maxI = i;
        }

        for(int j = K; j < K+M; j++){
            arr[maxI][j] = 1;
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
               System.out.print(arr[i][j] + " ");
            }

            System.out.println();
        }
    }
}