import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] map;
    static boolean[] visited;
    static int max;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        map = new int[N][N];
        visited = new boolean[N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        max = Integer.MIN_VALUE;
        dfs(0, 0);
        System.out.print(max);

    }

    static void dfs(int depth, int sum){
        if(depth == N){
            max = Math.max(max, sum);
            return;
        }

        for(int i = 0; i < N; i++){
            if(visited[i]) continue;

            visited[i] = true;
            dfs(depth+1, sum + map[depth][i]);
            visited[i] = false;
        }
    }
}