import java.io.*;
import java.util.*;

public class Main {
    static final int OFFSET = 20;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()) + OFFSET;

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());

        for(int i =0 ; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long[][] dp = new long[N][41];
        
        dp[0][-arr[0] + OFFSET] += 1;
        dp[0][arr[0] + OFFSET] += 1;

        

        for(int i = 1; i < N; i++){
            for(int num = 0; num < 41; num++){
                if(dp[i-1][num] == 0) continue;

                if(num - arr[i] >= 0){
                    if(dp[i][num-arr[i]] > 0){
                        dp[i][num-arr[i]] += dp[i-1][num];
                    }else{
                        dp[i][num-arr[i]] = dp[i-1][num];
                    }
                }

                if(num + arr[i] <= 40){
                    if(dp[i][num+arr[i]] > 0){
                        dp[i][num+arr[i]] += dp[i-1][num];
                    }else{
                        dp[i][num+arr[i]] = dp[i-1][num];
                    }
                }
            }
        }

        System.out.println(dp[N-1][M]);
    }
}