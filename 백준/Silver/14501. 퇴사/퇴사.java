import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n+2][2];

        for(int i = 1; i<n+1; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n+2];
        int max = 0 ;
        for(int i = 1; i<= n+1; i++){
            int next_day = i + arr[i][0];

            if(max < dp[i]) max = dp[i];

            if(next_day <= n+1){
                dp[next_day] = Math.max(dp[next_day], max+arr[i][1]);
            }

        }
        System.out.println(max);


    }
}