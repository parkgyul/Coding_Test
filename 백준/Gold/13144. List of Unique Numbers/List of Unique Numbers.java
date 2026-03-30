import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(arr[i], max);
        }

        int[] cnt = new int[max+1];
        long total = 0;
        int left = 0;

        for(int right = 0; right < N; right++){
            while(cnt[arr[right]] != 0){
                cnt[arr[left]] --;
                left++;
            }

            cnt[arr[right]] ++;
            total += (right - left + 1);
        }

        System.out.print(total);
    }
}