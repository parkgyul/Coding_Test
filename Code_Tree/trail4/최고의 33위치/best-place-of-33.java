import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][N];
        StringTokenizer st;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0 ; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = Integer.MIN_VALUE;

        for(int i =0 ; i < N-2; i++){
            for(int j = 0; j < N-2; j++){
                int sum = 0;
                for(int k = i; k < i+3; k++){
                    for(int l = j; l < j+3; l++){
                        sum += arr[k][l];
                    }
                }
                answer = Math.max(answer, sum);
            }
        }

        System.out.print(answer);
    }
}