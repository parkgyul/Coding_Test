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

        L[0] = (long) arr[0];
        R[N-1] = (long) arr[N-1];
        for(int i = 1 ; i < N; i++){
            L[i] = L[i-1] + (long)arr[i];
        }

        for(int i = N-2; i >= 0; i--){
            R[i] = R[i+1] + (long)arr[i];
        }

        long answer =0;

        for(int k = 1; k <= N-3; k++){
            if(L[k] != sum/2) continue;
            if(R[k+1] != sum/2) continue;
    
            int left = 0;
            int right = 0;
            for(int i = 0; i <= k-1; i++){
                if(2*L[i] == L[k]) left++;
            }

            for(int j = k+2; j <= N-1; j++){
                if(2*R[j] == R[k+1]) right++;
            }

            answer +=(left*right);
        }

        System.out.print(answer);
    }
}