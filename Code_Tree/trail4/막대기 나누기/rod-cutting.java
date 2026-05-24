import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] value = new int[N+1];
        int[] dp = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i =1 ; i <= N; i++){
            value[i] = Integer.parseInt(st.nextToken());
            dp[i] = value[i];
        }

        for(int i = 2; i <= N; i++){
            for(int j = i-1; j >= 1; j--){
                dp[i] = Math.max(dp[i], dp[j] + value[i - j]);
            }
        }

        System.out.print(dp[N]);
    }
}