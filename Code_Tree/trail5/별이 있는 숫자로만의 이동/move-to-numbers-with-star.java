import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N+1][N+1];

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] prefix = new int[N+1][N+1];

        for(int j = 1; j <= N; j++){
            for(int i = 1; i <= N; i++){
                prefix[i][j] = prefix[i-1][j] + arr[i][j];
            }
        }

        int max = 0;

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                int sum = 0;
                // 중심 : i, j
                for(int col = j-K; col <= j+K; col++){
                    if(col < 1 || col > N) continue;

                    int diff = K - Math.abs(j - col);

                    sum += (prefix[Math.min(i+diff, N)][col] - prefix[Math.max(0, i-diff-1)][col]);
                }
                max = Math.max(sum, max);
            }
        }

        System.out.print(max);
    }
}