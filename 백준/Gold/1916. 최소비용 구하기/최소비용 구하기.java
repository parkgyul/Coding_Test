import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 도시 개수
        int M = Integer.parseInt(br.readLine()); // 버스 개수

        List<Node>[] graph = new ArrayList[N+1];

        for(int i = 1; i <= N; i++){
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[start].add(new Node(end, cost));
        }


        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int[] costs = new int[N+1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        costs[start] = 0;
        pq.add(new Node(start, 0));

       
        while(!pq.isEmpty()){
            Node current = pq.poll();
            int city = current.city;
            int cost = current.cost;

            if(cost > costs[city]) continue;

            for(Node next : graph[city]){
                int nextCity = next.city;
                int nextCost = cost + next.cost;
                
                if(costs[nextCity] > nextCost){
                    costs[nextCity] = nextCost;
                    pq.add(new Node(nextCity, nextCost));
                    
                }
            }
        }

        System.out.print(costs[end]);

    }

    static class Node implements Comparable<Node>{
        int city, cost;
        Node(int city, int cost){
            this.city = city;
            this.cost = cost;
        }

        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}