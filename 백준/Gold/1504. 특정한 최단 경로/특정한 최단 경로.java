import java.io.IOException;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    static int N, E;
    static List<Node>[] nodes;
    static int p1, p2;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        nodes = new ArrayList[N+1];
        for(int i =1 ; i < N+1; i++){
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            nodes[a].add(new Node(b, c));
            nodes[b].add(new Node(a, c));
        }
        st = new StringTokenizer(br.readLine());
        p1 = Integer.parseInt(st.nextToken());
        p2 = Integer.parseInt(st.nextToken());

        int[] dist1 = dijkstra(1);
        int[] distP1 = dijkstra(p1);
        int[] distP2 = dijkstra(p2);
        
        int INF = Integer.MAX_VALUE;
        
        long case1 = (long)dist1[p1] + (long)distP1[p2] + (long)distP2[N];
        long case2 = (long)dist1[p2] + (long)distP2[p1] + (long)distP1[N];
        
        if(dist1[p1] == INF || distP1[p2] == INF || distP2[N] == INF) case1 = INF;
        if(dist1[p2] == INF || distP2[p1] == INF || distP1[N] == INF) case2 = INF;
        
        long result = Math.min(case1, case2);
        
        System.out.println(result >= INF ? -1 : result);
    }

    public static int[] dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(cur.cost > dist[cur.node])
                continue;
            
            for(Node next : nodes[cur.node]){
                int nextCost = cur.cost + next.cost;
                
                if(nextCost > dist[next.node])
                    continue;
                
                dist[next.node] = nextCost;
                pq.add(new Node(next.node, nextCost));
            }
        }
        
        return dist;
    }

    public static class Node implements Comparable<Node>{
        int node, cost;

        public Node(int node, int cost){
            this.node = node;
            this.cost = cost;
        }

        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }
}