import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
    
        int max = 0;

        for(int i =0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
            }
        }

        int cnt;

        int answerK = 1;
        int answerCnt = 0;

        for(int k = 1; k < max; k++){
            visited = new boolean[N][M];
            cnt = 0;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(map[i][j] > k && !visited[i][j]){
                        visited[i][j] = true;
                        cnt++;
                        dfs(i, j, k);
                    }
                }
            }

            if(cnt > answerCnt){
                answerK = k;
                answerCnt = cnt;
            }
        }

        System.out.print(answerK + " " + answerCnt);
    }

    static void dfs(int prevI, int prevJ, int k){
        for(int i =0 ; i < 4; i++){
            int ni = prevI + dx[i];
            int nj = prevJ + dy[i];

            if(ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
            if(map[ni][nj] <= k || visited[ni][nj]) continue;

            visited[ni][nj] = true;
            dfs(ni, nj, k);
        }
    }
}