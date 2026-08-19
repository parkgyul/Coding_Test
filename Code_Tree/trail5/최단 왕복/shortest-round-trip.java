import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int INF = 10000000;
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] costs = new int[N+1][N+1];

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(i == j) continue;
                costs[i][j] = INF;
            }
        }


        for(int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            int fr = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int co = Integer.parseInt(st.nextToken());

            costs[fr][to] = co;
        }
        for(int k = 1; k <= N; k++){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    costs[i][j] = Math.min(costs[i][j], costs[i][k] + costs[k][j]);
                }
            }
        }

        int min = INF;

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(i == j) continue;

                min = Math.min(min, costs[i][j] + costs[j][i]);
            }
        }

        System.out.print(min);
    }
}