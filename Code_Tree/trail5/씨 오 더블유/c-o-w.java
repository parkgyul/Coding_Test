import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        char[] arr = br.readLine().toCharArray();

        int[] C = new int[N];
        int[] W = new int[N];

        C[0] = (arr[0] == 'C' ? 1 : 0);
        W[N-1] = (arr[N-1] == 'W' ? 1 : 0);

        for(int i = 1; i < N; i++){
            C[i] = C[i-1] + (arr[i] == 'C' ? 1 : 0);
        }

        for(int i = N-2; i >= 0 ; i--){
            W[i] = W[i+1] + (arr[i] == 'W' ? 1 : 0);
        }
        long sum = 0;

        for(int i = 1; i < N-1; i++){
            if(arr[i] != 'O') continue;
            if(C[i-1] == 0 || W[i+1] == 0) continue;
            sum += (long)(C[i-1] * W[i+1]);
        }

        System.out.print(sum);
    }
}