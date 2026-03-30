import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.io.InputStreamReader;

public class Main{
    static int n, m;
    static long[][] costs;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args)throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        StringTokenizer st;

        costs = new long[n+1][n+1];
        for(int i = 1; i <= n; i++){
            Arrays.fill(costs[i], Long.MAX_VALUE);
            costs[i][i] = 0;
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            costs[start][end] = Math.min(costs[start][end], cost);
        }

        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    if(costs[i][k] != Long.MAX_VALUE && costs[k][j] != Long.MAX_VALUE){
                        costs[i][j] = Math.min(costs[i][j], costs[i][k] + costs[k][j]);
                    }
                }
            }
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
               sb.append(costs[i][j] == Long.MAX_VALUE ? 0 : costs[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }



    public static class Bus implements Comparable<Bus>{
        int city; long cost;
        public Bus(int city, long cost){
            this.city = city;
            this.cost = cost;
        }

        public int compareTo(Bus o){
            return Long.compare(this.cost, o.cost);
        }
    }
}