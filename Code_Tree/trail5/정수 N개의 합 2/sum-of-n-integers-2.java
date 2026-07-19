import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] prefix = new int[N];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(st.nextToken());

            prefix[i] = ((i >= 1) ? prefix[i-1] : 0) + num;
        }

        int max = -100;

        for(int i = K; i <= N-1; i++){
            max = Math.max(max, prefix[i]-prefix[i-K]);
        }

        System.out.println(max);
    }
}