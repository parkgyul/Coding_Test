import java.io.*;
import java.util.*;

public class Main {
    static final int OFFSET = 20;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()) + OFFSET;

        int[] arr = new int[N+1];

        st = new StringTokenizer(br.readLine());

        for(int i =1 ; i <= N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long[][] dp = new long[N+1][41];
        dp[0][20] = 1;


        for(int i = 1; i <= N; i++){
            for(int num = 0; num < 41; num++){
                // i 번쨰 수는 빼는 경우
                if(num + arr[i] <= 20 + OFFSET){
                    dp[i][num] += dp[i-1][num + arr[i]];
                }

                // i 번째 수는 더하는 경우
                if(num - arr[i] >= -20 + OFFSET){
                    dp[i][num] += dp[i-1][num - arr[i]];
                }
            }
        }

        System.out.println(dp[N][M]);
    }
}