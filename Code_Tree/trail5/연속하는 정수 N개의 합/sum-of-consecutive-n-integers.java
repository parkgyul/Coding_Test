import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] sum = new long[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            sum[i] = sum[i-1] + (long) Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int cnt = 0;
        for(int right = 1; right <= N; right++){

            if(sum[right] - sum[left] < M) continue;

            while(left < right && sum[right] - sum[left] > M){
                left++;
            }

            if(sum[right]-sum[left] == M) cnt ++;
        }

        System.out.print(cnt);
    }
}