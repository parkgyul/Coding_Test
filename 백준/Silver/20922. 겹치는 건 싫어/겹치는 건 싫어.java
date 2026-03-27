import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        int len = 0;
        int[] count = new int[100001];

        while(right < N){
            int cnt = count[arr[right]];

            if(cnt < K){
                count[arr[right]] ++;
                right++;
                len = Math.max(len, right - left);
            } else{
                count[arr[left]] --;
                left++;
            }
        }

        System.out.println(len);
    }
}