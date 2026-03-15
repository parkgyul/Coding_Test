import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;


import java.util.*;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int start = Integer.parseInt(br.readLine());

        List<Node>[] arr = new ArrayList[V+1];

        for(int i = 1; i <= V; i++){
            arr[i] = new ArrayList<>();
        }
        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            arr[s].add(new Node(e, cost));
        }

        boolean[] visited = new boolean[V+1];
        int[] costs = new int[V+1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[start] = 0;

        visited[start] = true;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));


        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            int e = cur.e;
            int cost = cur.cost;

            if(cost > costs[e]) continue;

            for (Node next : arr[e]) {
                int nextE = next.e;
                int newCost = cost + next.cost;
                if (costs[nextE] <= newCost)
                    continue;

                costs[nextE] = newCost;
                pq.add(new Node(nextE, newCost));
            }
        }

        for(int i = 1; i <= V; i ++){
            if(costs[i] == Integer.MAX_VALUE) System.out.println("INF");
            else System.out.println(costs[i]);
        }
    }

    public static class Node implements Comparable<Node>{
        int e, cost;

        public Node(int e, int cost){
            this.e = e;
            this.cost = cost;
        }

        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }
}