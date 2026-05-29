import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[] arr = new int[3*N];

        for(int i = 0; i < 3*N; i++){
            if(i%N  == 0) st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        while(T-- > 0){
            int temp = arr[3*N-1];
            for(int i = 3*N-2; i >= 0; i--){
                arr[i+1] = arr[i];
            }

            arr[0] = temp;
        }

        for(int i = 0; i < 3*N; i++){
            System.out.print(arr[i] + " ");
            if(i%N == N-1) System.out.println();
        }
    }
}