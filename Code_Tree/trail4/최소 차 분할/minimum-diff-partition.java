import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];

        int sum = 0;

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }

        boolean[] dp = new boolean[sum+1];
        dp[0] = true;
        dp[sum] = true;
        for(int n : arr){
            for(int j = sum; j >= n; j--){
                if(dp[j-n]){
                    dp[j] = true;
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for(int i =1 ; i <= sum; i++){
            if(dp[i]){
                answer = Math.min(answer, Math.abs(sum - 2*i));
            }
        }

        System.out.print(answer);

    }
}