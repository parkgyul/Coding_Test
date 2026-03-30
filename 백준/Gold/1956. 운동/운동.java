import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    static int V, E;
    static int[][] costs;
    static int min;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

       costs = new int[V+1][V+1];

       for(int i = 1; i < V+1; i++){
           Arrays.fill(costs[i], Integer.MAX_VALUE);
           costs[i][i] = 0;
       }
       
        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            costs[start][end] = Math.min(costs[start][end],cost);
        }


        min = Integer.MAX_VALUE;

        for(int k = 1; k < V+1; k++){
            for(int i = 1; i < V+1; i++){
                for(int j = 1; j < V+1; j++){
                    if(i == j) continue;
                    if(costs[i][k] != Integer.MAX_VALUE && costs[k][j] != Integer.MAX_VALUE)
                        costs[i][j] = Math.min(costs[i][j], costs[i][k] + costs[k][j]);
                }
            }
        }

        int result = Integer.MAX_VALUE;

        for(int i = 1; i < V+1; i++){
            for(int j = 1; j < V+1; j++){
                if(i == j)
                    continue;
                if(costs[i][j] != Integer.MAX_VALUE && costs[j][i] != Integer.MAX_VALUE)
                    result = Math.min(result, costs[i][j]+ costs[j][i]);
            }
        }


        System.out.print(result == Integer.MAX_VALUE ? -1 : result);
    }
}