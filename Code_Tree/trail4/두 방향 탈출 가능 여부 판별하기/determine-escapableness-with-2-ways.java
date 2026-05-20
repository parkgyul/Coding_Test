import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1,0};
    static int[] dy = {0,1};
    static boolean flag;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i =0 ; i < N; i++){
            st = new StringTokenizer(br.readLine()); 
            for(int j =0 ; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        flag = false;
        visited[0][0]= true;
        dfs(0, 0);
       

        if(flag)System.out.print(1);
        else System.out.print(0);
    }

    static void dfs(int prevI, int prevJ){
        if(flag) return;
        
        for(int i =0 ; i < 2; i++){
            int ni = prevI + dx[i];
            int nj = prevJ + dy[i];

            if(ni == N-1 && nj == M-1){
                flag = true;
                return;
            }

            if(ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
            if(visited[ni][nj] || map[ni][nj] == 0) continue;

            visited[ni][nj] = true;
            dfs(ni, nj);
        }
    }
}
