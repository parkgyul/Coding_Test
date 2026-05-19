import java.io.*;
import java.util.*;
public class Main {
    static int N;
    static int[][] map;
    static int min;
    static boolean[] visited;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        min = Integer.MAX_VALUE;
        visited = new boolean[N];

        dfs(0, 0, 0);

        System.out.print(min);
    }
    static void dfs(int depth, int sum, int prev){
        if(sum >= min){
            return;
        }

        if(depth == N-1){
            if(map[prev][0] != 0){
                min = Math.min(min, sum + map[prev][0]);
            }
            return;
        }

        for(int i = 1; i < N; i++){
            if(visited[i] || map[prev][i] == 0) continue;

            visited[i] = true;
            dfs(depth+1, sum + map[prev][i], i);
            visited[i] = false;
        }
    }
}