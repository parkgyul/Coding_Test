import java.io.*;
import java.util.*;

public class Main {
    static List<Node>[] nodes;
    static int[] costs, ways;
    static List<Integer> path;
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        nodes = new ArrayList[N+1];
        
        costs = new int[N+1];
        Arrays.fill(costs, INF);

        ways = new int[N+1];

        for(int i = 0; i <= N; i++){
            nodes[i] = new ArrayList<>();
        }

        path = new ArrayList<>();

        for(int i =1 ; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            nodes[e].add(new Node(s, c));
            nodes[s].add(new Node(e, c));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start);

        StringBuilder sb = new StringBuilder();
        System.out.println(costs[end]);
        path.add(end);
        while(end != start){
            end = ways[end];
            path.add(end);
        }

        for(int i = path.size()-1; i >= 0; i--){
            sb.append(path.get(i)).append(" ");
        }

        System.out.print(sb);
    }

    static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(costs[cur.node] < cur.cost) continue;

            for(Node next : nodes[cur.node]){
                int nextCost = next.cost + cur.cost;

                if(nextCost >= costs[next.node]) continue;

                ways[next.node] = cur.node;
                costs[next.node] = nextCost;
                pq.add(new Node(next.node, nextCost));
            }
        }
    }
    
    static class Node implements Comparable<Node>{
        int node, cost;
        
        Node(int node, int cost){
            this.node = node;
            this.cost = cost;
        }

        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }
}
