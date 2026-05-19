import java.io.*; 
import java.util.*; 

public class Main {
    static int N;
    static boolean[] visited;
    static int[] arr;
    static StringBuilder sb;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        visited = new boolean[N+1];
        arr = new int[N];
        dfs(0);

        System.out.print(sb);
    }

    static void dfs(int depth){
        if(depth == N){
            for(int i =0; i < N; i++){
                sb.append(arr[i] + " ");
            }

            sb.append("\n");
            return;            
        }
        for(int i = 1; i <= N; i++){
            if(visited[i]) continue;

            arr[depth] = i;
            visited[i] = true;
            dfs(depth+1);
            visited[i] = false;
        }
    }


}