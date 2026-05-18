import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static int min;
    static int total;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[2*N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i =0 ; i < 2*N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            total += arr[i];
        }

        min = Integer.MAX_VALUE;

        dfs(1, 0, 0);

        System.out.print(min);
    }

    static void dfs(int cnt, int index, int sum){
        if(cnt == N + 1){
            min = Math.min(min, Math.abs(total-sum-sum));
            return;
        }

        for(int i = index; i < 2*N; i++){
            dfs(cnt+1, i+1, sum+arr[i]);
        }
    }
}