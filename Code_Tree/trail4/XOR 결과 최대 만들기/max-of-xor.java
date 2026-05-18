import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr;
    static int max;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        max = Integer.MIN_VALUE;

        for(int i = 0; i < N; i++){
            dfs(1, i+1, arr[i]);
        }

        System.out.print(max);
    }

    static void dfs(int depth, int index, int sum){
        if(depth == M){
            max = Math.max(sum, max);
            return;
        }

        for(int i = index; i < N; i++){
            dfs(depth+1, i+1, sum^arr[i]);
        }
    }
}