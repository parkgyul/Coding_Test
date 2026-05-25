import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Quest> list = new ArrayList<>();

        int ex = 0;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            list.add(new Quest(e, t));

            ex += e;
        }

        int[] dp = new int[ex+1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        int answer = Integer.MAX_VALUE;

        for(int i = 0; i < N; i++){
            Quest q = list.get(i);
            for(int j = ex; j >= q.e; j--){
                if(j - q.e != 0 && dp[j - q.e] != Integer.MAX_VALUE){
                    dp[j] = Math.min(dp[j], dp[j-q.e] + q.t);
                }

                if(j-q.e == 0){
                    dp[j] = Math.min(dp[j], q.t);
                }
            }
        }

        for(int i = M; i < ex+1; i++){
            answer = Math.min(answer, dp[i]);
        }

        System.out.print(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static class Quest{
        int e, t;
        Quest(int e, int t){
            this.e = e;
            this.t = t;
        }
    }
}