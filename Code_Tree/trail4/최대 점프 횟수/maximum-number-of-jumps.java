import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[] dp = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = Integer.MIN_VALUE;
        }

        dp[0] = 0;

        int answer = 0;
        for(int i = 1; i < N; i++){
            for(int j = 0; j < i; j++){
                if(dp[j] == Integer.MIN_VALUE) continue;

                if(j + arr[j] >= i){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            answer = Math.max(answer, dp[i]);
        }

        System.out.print(answer);
    }
}