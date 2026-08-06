import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        int[] sum = new int[N];
        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(br.readLine()) % 7;
            sum[i] += (i == 0 ? num : sum[i-1] + num);
        }

        int max = 0;
        for(int i = N-1; i >= 0; i--){
            if(sum[i] % 7 == 0){
                max = Math.max(max, i+1);
                continue;
            }

            for(int j = 0; j <= i-1; j++){
                if(i - j <= max) break;

                if((sum[i] - sum[j]) % 7== 0){
                    max = Math.max(max, i-j);
                    break;
                }
            }
        }

        System.out.print(max);
    }
}