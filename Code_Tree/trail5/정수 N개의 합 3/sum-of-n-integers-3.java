import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N+1][N+1];
        int[][] prefix = new int[N+1][N+1];

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i <= N; i++){
            for(int j =1 ; j <= N; j++){
                prefix[i][j] = prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1] + arr[i][j];
            }
        }

        int max = Integer.MIN_VALUE;

        for(int i = 1; i <= N-K+1; i++){
            for(int j = 1; j <= N-K+1; j++){
                int sum = prefix[i+K-1][j+K-1] - prefix[i-1][j+K-1] - prefix[i+K-1][j-1] + prefix[i-1][j-1];
                max = Math.max(max, sum);
            }
        }

        System.out.println(max);
    }
}