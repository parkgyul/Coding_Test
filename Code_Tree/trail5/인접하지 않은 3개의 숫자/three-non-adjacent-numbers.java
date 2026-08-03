import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] L = new int[N];
        int[] R = new int[N];

        L[0] = arr[0];
        R[N-1] = arr[N-1];

        for(int i = 1; i < N; i++){
            L[i] = Math.max(L[i-1], arr[i]);
        }

        for(int i = N-2; i >= 0; i--){
            R[i] = Math.max(R[i+1], arr[i]);
        }

        int max = Integer.MIN_VALUE;

        for(int i = 2; i < N-2; i++){
            max = Math.max(max, L[i-2] + arr[i] + R[i+2]);
        }

        System.out.print(max); 
    }

}