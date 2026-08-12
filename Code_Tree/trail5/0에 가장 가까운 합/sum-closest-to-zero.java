import java.io.*; 
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

    
        int min = Integer.MAX_VALUE;

        Arrays.sort(arr);

        int right = N-1;

        for(int left = 0; left < N; left++){
            if(left < right){
                min = Math.min(min, Math.abs(arr[right] + arr[left]));
            }

            while(left < right-1 && arr[right] + arr[left] > 0){
                right --;
                min = Math.min(min, Math.abs(arr[right] + arr[left]));
            }
        }
        

        System.out.print(min);

    }
}