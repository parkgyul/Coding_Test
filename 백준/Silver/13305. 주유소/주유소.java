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

        Queue<Integer> dist = new LinkedList<>();
        Queue<Integer> cost = new LinkedList<>();

        for(int i = 0; i < n-1; i++){
            dist.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            cost.add(Integer.parseInt(st.nextToken()));
        }

        int total = 0;
        int cost_now = Integer.MAX_VALUE;
        int distance_now;
        while (!dist.isEmpty()) {
            distance_now = dist.poll();
            cost_now = Math.min(cost_now, cost.poll());
            total += cost_now * distance_now;
        }

        System.out.print(total);
    }

}