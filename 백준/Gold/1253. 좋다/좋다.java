import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
    static long[] arr;
    static int N;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new long[N];
        int cnt = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i= 0; i < N; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        for(int i = 0; i < N; i++){
            if (isGood(i)) {
                cnt++;
            }
        }

        System.out.print(cnt);
    }

    public static boolean isGood(int index){
        int left = 0;
        int right = N-1;

        if(index == 0) left = 1;
        if(index == N-1) right = N-2;

        while(left < right){
            long sum = arr[left] + arr[right];

            if(sum == arr[index])
                return true;
            else if(sum > arr[index]){
                right --;
                if(right == index)
                    right --;
            }

            else {
                left++;
                if(left == index)
                    left++;
            }
        }

        return false;
    }
}