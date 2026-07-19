import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+1];

        for(int i = 1; i <= N; i++){
            arr[i] = i;
        }

        for(int i = 0; i < B; i++){
            int num = Integer.parseInt(br.readLine());
            arr[num] = -1;
        }

        int[] prefix = new int[N+1];
        for(int i = 1; i <= N; i++){
            if(arr[i] == -1){
                prefix[i] = prefix[i-1] + 1;
            }else{
                prefix[i] = prefix[i-1];
            }
        }
        int min = Integer.MAX_VALUE;

        for(int i = K; i <= N; i++){
            min = Math.min(min, prefix[i] - prefix[i-K]);
        }

        System.out.print(min);
    }
}