import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.io.BufferedReader;

import java.util.Queue;

class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] dist = new int[n-1];
        int[] cost = new int[n];

        for(int i = 0; i < n-1; i++){
            dist[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            cost[i] = Integer.parseInt(st.nextToken());
        }

        long total = 0;
        int cost_now = cost[0];
        int distance_now;
        for(int i = 0; i<n-1;i++){
            distance_now = dist[i];
            cost_now = Math.min(cost[i], cost_now);

            total += (long)distance_now * cost_now;
        }

        System.out.print(total);
    }

}