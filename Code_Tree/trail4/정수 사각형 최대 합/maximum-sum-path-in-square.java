import java.io.*; 
import java.util.*; 

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        int[][] map = new int[N][N];
        int[][] dp = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = map[0][0];

        for(int i =0 ; i < N; i++){
            for(int j = 0; j < N; j++){
                int up = Integer.MIN_VALUE; 
                int left = Integer.MIN_VALUE;

                if(i-1 >= 0) up = dp[i-1][j];
                if(j-1 >= 0) left = dp[i][j-1];

                dp[i][j] = Math.max(dp[i][j], Math.max(up, left) + map[i][j]);
            }
        }

        System.out.print(dp[N-1][N-1]);
    }
}