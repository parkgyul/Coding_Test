import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] prev = new int[1000001];
        int max = -1;
        Arrays.fill(prev, -1);


        for(int i = N-1; i >= 0; i--){
            if(prev[arr[i]] != -1 && prev[arr[i]] - i <= K){
                max = Math.max(arr[i], max);
            }

            prev[arr[i]] = i;
        }

        System.out.print(max);
    }
}