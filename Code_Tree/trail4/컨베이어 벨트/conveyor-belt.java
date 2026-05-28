import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[N*2];

        for(int i =0 ; i < 2*N; i++){
            if(i % N == 0) st = new StringTokenizer(br.readLine());

            arr[i] = Integer.parseInt(st.nextToken());
        }
    
        for(int i = 0; i < T; i++){
            int temp = arr[2*N -1];
            for(int j = 2*N-2; j >= 0; j--){
                arr[j+1] = arr[j];
            }

            arr[0] = temp;
        }


        for(int i =0 ; i < 2*N; i++){
            System.out.print(arr[i] + " ");
            if(i == N-1) System.out.println();
        }
    }
}