import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];

        for(int i =0 ; i < N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] lis = new int[N];

        int size = 0;

        for(int i = 0; i < N; i++){
            int x = arr[i];
            int left = 0, right = size;

            while(left < right){
                int mid = (left + right) / 2;
                if(lis[mid] < x) left = mid+1;
                else right = mid;
            }

            lis[left] = x;
            if(left == size) size++;
        }

        System.out.print(N - size);
    }
}