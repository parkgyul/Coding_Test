import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int cnt = 0;
        int right = N-1;

        for(int left = 0; left < N; left++){
            while(right != 0 && arr[left] + arr[right] > M){
                right--;
            }

            if(right <= left) break;

            cnt += (right - left);
        }

        System.out.print(cnt);        
    }
}