import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        int N = Integer.parseInt(br.readLine());

        int[] distance = new int[N];
        int[] cost = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N-1; i++){
            distance[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            cost[i] = Integer.parseInt(st.nextToken());
        }

        long[] min = new long[N-1];
        Arrays.fill(min, Long.MAX_VALUE);
        min[0] = cost[0];
        for(int i = 1; i < N-1; i++){
            min[i] = Math.min(min[i-1], cost[i]);
        }

        long sum = 0;
        for(int i = 0; i < N-1; i++){
            sum += (min[i] * distance[i]);
        }

        System.out.print(sum);
    }
}
