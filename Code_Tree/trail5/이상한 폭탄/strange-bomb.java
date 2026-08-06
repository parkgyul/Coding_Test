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

        int[] max = new int[N];
        Arrays.fill(max, -1);

        for(int i = N-2; i >= 0; i--){
            max[i] = max[i+1];
            
            if(max[i+1] >= arr[i]){
                continue;
            }

            for(int j = i+1; j <= i+K && j <= N-1; j++){
                if(arr[i] == arr[j]){
                    max[i] = arr[i];
                    break;
                }
            }
        }

        System.out.print(max[0]);
    }
}