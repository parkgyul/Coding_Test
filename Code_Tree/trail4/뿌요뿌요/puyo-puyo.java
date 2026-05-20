import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int cnt;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];
        
        int max = 0;
        int min = 101;
        StringTokenizer st;
        for(int i =0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0 ; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, map[i][j]);
                max = Math.max(max, map[i][j]);
            }
        }
        int burstCnt = 0;
        int maxBlock = 1;
        for(int k = min; k <= max; k++){
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(!visited[i][j] && map[i][j] == k){
                        cnt = 0;

                        dfs(i, j, k);
                        if(cnt >= 4) burstCnt ++;
                        maxBlock = Math.max(maxBlock, cnt);
                    }
                }
            }
        }

        System.out.print(burstCnt + " " + maxBlock);
    }

    static void dfs(int prevI, int prevJ, int k){
        for(int i =0 ; i < 4; i++){
            int ni = prevI + dx[i];
            int nj = prevJ + dy[i];

            if(ni < 0 || ni >= N || nj < 0 || nj >= N) continue;
            if(visited[ni][nj] || map[ni][nj] != k) continue;

            visited[ni][nj] = true;
            cnt++;
            dfs(ni, nj, k);
        }
    }
}