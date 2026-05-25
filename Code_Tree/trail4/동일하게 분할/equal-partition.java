import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int[] arr = new int[N];

        int sum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }

        boolean[] dp = new boolean[sum+1];
        dp[0] = true;

        for(int i = 0; i < N; i++){
            for(int j = sum; j >= arr[i]; j--){
                if(dp[j - arr[i]]){
                    dp[j] = true;
                }
            }
        }

        System.out.print((sum % 2 == 0 && dp[sum/2]) ? "Yes" : "No");
    }
}