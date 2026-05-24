import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        final int INF = Integer.MIN_VALUE;

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] dp = new int[M+1]; // 해당 무게 일 때 가치 최고 저장.
        int[] w = new int[N];
        int[] v = new int[N];

        for(int i =0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int wi = Integer.parseInt(st.nextToken());
            int vi = Integer.parseInt(st.nextToken());

            w[i] = wi;
            v[i] = vi;
        }

        Arrays.fill(dp, INF);
        dp[0] = 0;
        int answer = INF;

        
        for(int j = 0; j < N; j++){
            for(int i = M; i >= 0; i--){
                if(i-w[j] >= 0 && dp[i-w[j]] != INF){
                    dp[i] = Math.max(dp[i], dp[i-w[j]] + v[j]);
                }
                answer = Math.max(answer, dp[i]);
            }
        }

        System.out.print(answer);

    }
}