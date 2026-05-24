import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[] incr = new int[N];
        int[] decr = new int[N];

        Arrays.fill(incr, 1);
        Arrays.fill(decr, 1);

        int answer = 1;

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i =0 ; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i < N; i++){
            for(int j = 0; j < i; j++){
                if(arr[i] > arr[j]){
                    incr[i] = Math.max(incr[i], incr[j] + 1);
                }
            }

            answer = Math.max(answer, incr[i]);
        }
        
        

        for(int i = N-1 ; i >= 0; i--){
            for(int j = i+1; j < N; j++){
                if(arr[i] > arr[j]){
                    decr[i] = Math.max(decr[i], decr[j] + 1);
                }
            }
            answer = Math.max(answer, decr[i]);
        }

        for(int i =0 ; i < N; i++){
            answer = Math.max(answer, decr[i] + incr[i] - 1);
        }

        System.out.print(answer);
    }
}