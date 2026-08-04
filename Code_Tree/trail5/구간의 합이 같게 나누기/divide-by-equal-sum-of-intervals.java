import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        int[] arr = new int[N];
        long[] L = new long[N];
        long[] R = new long[N];

        long sum = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }

        long answer = 0;

        if(sum % 4 != 0){
            System.out.print(answer);
            return;
        }

        L[0] = 0;
        int partSum = arr[0];
        int cnt = (partSum == sum/4) ? 1: 0;
        for(int i = 1 ; i < N; i++){
           partSum += arr[i];

           if(partSum == 2 * sum/4) L[i] = cnt;

           if(partSum == sum/4) cnt++;
        }

        R[N-1] = 0;
        partSum = arr[N-1];
        cnt = (partSum == sum/4) ? 1: 0;

        for(int i = N-2; i >= 0; i--){
            partSum += arr[i];

            if(partSum == 2 * sum/4){
                R[i] = cnt;
            }

            if(partSum == sum/4) cnt++;
        }

        long ans = 0;
        for(int i = 1; i < N-1; i++){
            answer += (long) L[i] * R[i+1];
        }

        System.out.print(answer);
    }
}