import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] arr;
    static int min;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        min = Integer.MAX_VALUE;

        dfs(0, 0);
        System.out.print(min == Integer.MAX_VALUE ? -1 : min);
    }

    static void dfs(int cur, int cnt){
        int max = arr[cur];
        for(int i = 1; i <= max; i++){
            if(cur+i >= N-1){
                min = Math.min(min, cnt+1);
                continue;
            }
            dfs(cur+i, cnt+1);
        }

    }
}