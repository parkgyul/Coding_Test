import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 10000000;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] costs = new int[N+1][N+1];

        for(int i = 1; i <= N; i++){
            for(int j =1; j <= N; j++){
                if(i == j) continue;

                costs[i][j] = INF;
            }
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int fr = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int co = Integer.parseInt(st.nextToken());

            costs[fr][to] = co;
            costs[to][fr] = co;
        }

        for(int k = 1; k <= N; k++){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    costs[i][j] = Math.min(costs[i][j], costs[i][k] + costs[k][j]);
                }
            }
        }

        int min = Math.min(INF, costs[v1][e] + costs[v2][e]);
        for(int k = 1; k <= N; k++){
            min = Math.min(min, costs[v1][k] + costs[v2][k] + costs[k][e]);
        }
        System.out.print(min >= INF ? -1 : min);
    }
}