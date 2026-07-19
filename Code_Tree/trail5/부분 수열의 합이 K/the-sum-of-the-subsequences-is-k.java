import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] prefix = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < N+1; i++){
            int num = Integer.parseInt(st.nextToken());
            prefix[i] = prefix[i-1] + num;
        }

        int cnt = 0;

        for(int i = N; i >= 1; i--){
            for(int j = i-1; j >= 0; j--){
                if(prefix[i] - prefix[j] > K) break;

                if(prefix[i] - prefix[j] == K) cnt++;
            }
        }

        System.out.println(cnt);
    }
}