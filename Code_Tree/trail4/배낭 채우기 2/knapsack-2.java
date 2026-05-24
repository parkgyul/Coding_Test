import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        final int INF = Integer.MIN_VALUE;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] dp = new int[M+1];
        int[] weight = new int[N];
        int[] value = new int[N];

        Arrays.fill(dp, INF);

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = 0;

        int answer = 0;

        for(int i = 1; i <= M; i++){
            for(int j = 0; j < N; j++){
                if(i - weight[j] >= 0 && dp[i-weight[j]] != INF){
                    dp[i] = Math.max(dp[i], dp[i-weight[j]] + value[j]);
                }
            }
            answer = Math.max(dp[i], answer);
        }

        System.out.print(answer);
    }
}