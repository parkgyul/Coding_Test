import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = Integer.MIN_VALUE;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(i < N-1 && j < M-1){
                    int sum = 0;
                    for(int k = i; k < i+2; k++){
                        for(int l = j; l < j+2; l++){
                            sum += arr[k][l];
                        }
                    }

                    answer = Math.max(answer, sum - arr[i][j]);
                    answer = Math.max(answer, sum - arr[i+1][j]);
                    answer = Math.max(answer, sum - arr[i][j+1]);
                    answer = Math.max(answer, sum - arr[i+1][j+1]);
                }

                if(j < M-2){
                    int sum = arr[i][j] + arr[i][j+1] + arr[i][j+2];
                    answer = Math.max(answer, sum);
                }

                if(i < N-2){
                    int sum = arr[i][j] + arr[i+1][j] + arr[i+2][j];
                    answer = Math.max(answer, sum);
                }
            }
        }

        System.out.print(answer);
    }
}