import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] si = new int[N+1];
        int[] ei = new int[N+1];
        int[] vi = new int[N+1];

        final int INF = 100000000;
    

        int[][] dp = new int[M+1][N+1];
        for(int i = 1; i <= M; i++){
            Arrays.fill(dp[i], -INF);
        }


        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if(s == 1) dp[1][i] = 0;

            si[i] = s;
            ei[i] = e;
            vi[i] = v;
        }

        for(int i = 2; i <= M; i++){
            for(int j = 1; j <= N; j++){
                if(si[j] > i || ei[j] < i ) continue;

                for(int k = 1; k <= N; k++){
                    if(dp[i-1][k] == -INF) continue;
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][k] +  Math.abs(vi[k] - vi[j]));
                }
            }
        }

        int answer = 0;


        for(int j = 1; j <= N; j++){
            answer = Math.max(answer, dp[M][j]);
        }

        System.out.print(answer);
    }
}