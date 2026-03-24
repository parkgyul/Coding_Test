import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int left = 0;
        int right = N-1;
        int min = Integer.MAX_VALUE;

        int[] max_index = new int[2];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int temp;

        while(left < right){
            temp = arr[left] + arr[right];
            if(min > Math.abs(temp)){
                min = Math.abs(temp);
                max_index[0] = left;
                max_index[1] = right;
            }

            if(temp > 0)
                right--;
            else if(temp < 0)
                left++;
            else{
                max_index[0] = left;
                max_index[1] = right;
                break;
            }
        }

        System.out.print(arr[max_index[0]] + " " + arr[max_index[1]]);
    }

}