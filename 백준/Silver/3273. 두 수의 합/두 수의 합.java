import java.io.IOException;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sum = Integer.parseInt(br.readLine());

        Arrays.sort(arr);

        int left = 0;
        int right = n-1;
        for(int i =0 ; i < n; i++){
            if(arr[i] < sum) right = i;
        }

        int result = 0;
        while(left < right){
            int total = arr[left] + arr[right];
            if( total == sum) {
                left++;
                right --;
                result++;
            }else if(total > sum){
                right --;
            }else{
                left++;
            }
        }

        System.out.print(result);
    }
}