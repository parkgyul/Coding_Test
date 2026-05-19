import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static boolean[] visited;
    static StringBuilder sb;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        visited = new boolean[N+1];
        sb = new StringBuilder();
        dfs(0);

        System.out.print(sb);
    }

    static void dfs(int depth){
        if(depth == N){
            for(int i =0 ; i < N; i++){
                sb.append(arr[i] + " ");
            }
            sb.append("\n");

            return;
        }

        for(int i = N; i >= 1; i--){
            if(visited[i]) continue;

            visited[i] = true;
            arr[depth] = i;
            dfs(depth+1);
            visited[i] = false;
        }
    }
}