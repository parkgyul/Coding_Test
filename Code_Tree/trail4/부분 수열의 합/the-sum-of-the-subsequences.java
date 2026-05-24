import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] num = new int[N];
        boolean[] dp = new boolean[M+1];

        st = new StringTokenizer(br.readLine());
        for(int i =0 ; i < N; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = true;

        for(int i = 0; i < N; i++){
            for(int j = M; j >= 0; j--){
                if(j-num[i] >= 0 && dp[j-num[i]]){
                    dp[j] = true;

                    if(j == M){
                        System.out.print("Yes");
                        return;
                    }
                }
            }
        }

        System.out.print("No");
    }
}