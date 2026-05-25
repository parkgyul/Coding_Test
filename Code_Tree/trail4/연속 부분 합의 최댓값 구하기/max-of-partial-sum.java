import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];
        int[] dp = new int[N];

        for(int i =0 ; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = arr[0];

        int max = dp[0];

        for(int i =1 ; i < N; i++){
            dp[i] = Math.max(arr[i], dp[i-1] + arr[i]);
            max = Math.max(dp[i], max);
        }

        System.out.print(max);
    }
}