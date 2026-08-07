import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int min = Integer.MAX_VALUE;
        long sum = 0;
        int j = 0;
        for(int i = 1; i <= N; i++){
            while(j+1 <= N && sum < K){
                sum += arr[j+1];
                j++;
            }

            if(sum < K) break;

            min = Math.min(min, j-i+1);

            sum -= arr[i];
        }

        System.out.print(min == Integer.MAX_VALUE ? -1 : min);
    }
}