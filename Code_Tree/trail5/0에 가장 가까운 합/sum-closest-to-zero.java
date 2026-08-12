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

        int left = 0; 
        int right = N-1;
        int min = Integer.MAX_VALUE;

        Arrays.sort(arr);

        while(left < right){

            int diff = arr[right] + arr[left];
            min = Math.min(min, Math.abs(diff));
            if(min == 0) break;

            if(diff > 0){
                right--;
            }else if(diff < 0){
                left++;
            }
        }

        System.out.print(min);

    }
}