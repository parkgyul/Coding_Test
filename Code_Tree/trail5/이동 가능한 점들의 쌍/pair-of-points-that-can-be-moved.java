import java.io.*;
import java.util.*; 

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int INF = (int)1e9;

        
        ;
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int[][] costs = new int[N+1][N+1];
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                costs[i][j] = INF;

                if(i == j) costs[i][j] = 0;
            }
        }

        for(int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());

            int fr = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int co = Integer.parseInt(st.nextToken());

           costs[fr][to] =Math.min( costs[fr][to] , co);
        }


        for(int k = 1; k <= N; k++){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    costs[i][j] = Math.min(costs[i][j], costs[i][k] + costs[k][j]);
                }
            }
        }

        int cnt = 0;
        long sum = 0;
        for(int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine());

            int fr = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            int cost = INF;
            for(int k = 1; k <= P; k++){
                cost = Math.min(cost, costs[fr][k] + costs[k][to]);
            }

            if(cost >= INF) continue;

            cnt++;
            sum +=(long) cost;
        }

        System.out.println(cnt);
        System.out.print(sum);
    }
}