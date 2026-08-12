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
            sum[i] = (long) Integer.parseInt(st.nextToken());
        }

        int right = 0;
        int sumVal = 0;
        int cnt = 0;
        for(int left = 1; left <= N; left++){

            while(right+1 <= N && sumVal < M){
                sumVal += sum[right+1];
                right++;
            }

            if(sumVal == M){
                cnt++;
            }

            sumVal -= sum[left];
        }

        System.out.print(cnt);
    }
}