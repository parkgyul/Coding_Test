import java.io.*;
import java.util.*;
public class Main {
    static int N;
    static int[][] nums;
    static int[][] dirs;
    static int[][] dp;

    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static int max;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        nums = new int[N][N];
        dirs = new int[N][N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                dirs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken())-1;
        int c = Integer.parseInt(st.nextToken())-1;


        System.out.println(dfs(r,c));
        
    }
    static int dfs(int r, int c){
        if(dp[r][c] != -1){
            return dp[r][c];
        }

        dp[r][c] = 0;
        int dir = dirs[r][c] -1;

        for(int dist = 1; dist < N; dist++){
            int nr = r + dist * dx[dir];
            int nc = c + dist * dy[dir];

            if(nr <0 || nc < 0 || nr >= N || nc >= N) break;

            if(nums[nr][nc] <= nums[r][c]) continue;

            dp[r][c] = Math.max(dp[r][c], 1 + dfs(nr, nc));
        }

        return dp[r][c]; 
    }
}