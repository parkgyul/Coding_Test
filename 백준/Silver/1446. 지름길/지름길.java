import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        List<Node>[] nodes = new ArrayList[D+1];

        for(int i = 0; i <= D; i++){
            nodes[i] = new ArrayList<>();
        }

        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if(end > D) continue;

            nodes[start].add(new Node(end, cost));
        }

        int[] costs = new int[D+1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[0] = 0;

        for(int i = 0; i <= D-1; i++){
            costs[i+1] = Math.min(costs[i+1], costs[i] + 1);

            for(Node node : nodes[i]){
                int next = node.end;
                int next_cost = node.cost;

                if(next_cost + costs[i] < costs[next]) {
                    costs[next] = next_cost + costs[i];
                }
            }

        }


        System.out.print(costs[D]);
    }

    public static class Node{
        int end, cost;
        public Node(int end, int cost){
            this.end = end;
            this.cost = cost;
        }
    }
}