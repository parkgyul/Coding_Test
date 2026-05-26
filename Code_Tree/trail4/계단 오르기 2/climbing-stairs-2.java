import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N+1];
        int[][] dp = new int[N+1][4];

        for(int i =1 ; i <= N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        for(int i = 0; i <= N; i++){
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }

        dp[0][0] = 0;

        for(int i = 1; i <= N; i++){
            for(int cnt = 0; cnt <= 3; cnt++){
                // i-1 층에서 1칸 올라오는 경우
                if(i >= 1 && cnt -1 >= 0 && dp[i-1][cnt-1] != Integer.MIN_VALUE){
                    dp[i][cnt] = Math.max(dp[i][cnt], dp[i-1][cnt-1] + arr[i]);
                }

                // i-2 층에서 2칸 올라오는 경우
                if(i >= 2 && dp[i-2][cnt] != Integer.MIN_VALUE){
                    dp[i][cnt] = Math.max(dp[i][cnt], dp[i-2][cnt] + arr[i]);
                }
            }
        }

        int answer = Integer.MIN_VALUE;
        for(int i = 0; i <= 3; i++){
            answer = Math.max(dp[N][i], answer);
        }

        System.out.print(answer);
    }
}