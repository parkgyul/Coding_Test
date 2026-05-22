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
            for(int j = 0 ; j <N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = map[0][0];

        for(int i =1; i < N; i++){
            dp[i][0] = Math.max(dp[i-1][0], map[i][0]);
        }

        for(int j =1; j < N; j++){
            dp[0][j] = Math.max(dp[0][j-1], map[0][j]);
        }

        for(int i = 1; i < N; i++){
            for(int j = 1; j < N; j++){
                dp[i][j] = Math.max(Math.min(dp[i-1][j], dp[i][j-1]), map[i][j]);
            }
        }

        System.out.print(dp[N-1][N-1]);
    }
}