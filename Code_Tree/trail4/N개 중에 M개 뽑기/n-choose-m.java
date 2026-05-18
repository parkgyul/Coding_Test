import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr;
    static StringBuilder sb;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[M];

        sb = new StringBuilder();
        dfs(0, 1);

        System.out.print(sb);
    }

    static void dfs(int depth, int num){
        if(depth == M){
            for(int i =0 ; i < M; i++){
                sb.append(arr[i]+ " ");
            }

            sb.append("\n");
            return;
        }

        for(int i = num; i <= N; i++){
            arr[depth] = i;
            dfs(depth+1, i+1);
        }
    }
}