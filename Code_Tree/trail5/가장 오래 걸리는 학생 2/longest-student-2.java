import java.io.*;
import java.util.*;

public class Main {
    static List<Node>[] nodes;
    static int[] time;
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        nodes = new ArrayList[N+1];
        
        time = new int[N+1];
        Arrays.fill(time, INF);
        time[N] = 0;

        for(int i = 0; i <= N; i++){
            nodes[i] = new ArrayList<>();
        }

        for(int i =1 ; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            nodes[e].add(new Node(s, t));
        }

        dijkstra(N);

        int max = -INF;
        for(int i = 1; i < N; i++){
            max = Math.max(max, time[i]);
        }

        System.out.print(max);
    }

    static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(time[cur.node] < cur.t) continue;

            for(Node next : nodes[cur.node]){
                int nextTime = next.t + cur.t;

                if(nextTime >= time[next.node]) continue;

                time[next.node] = nextTime;
                pq.add(new Node(next.node, nextTime));
            }
        }
    }
    
    static class Node implements Comparable<Node>{
        int node, t;
        
        Node(int node, int t){
            this.node = node;
            this.t = t;
        }

        public int compareTo(Node o){
            return this.t - o.t;
        }
    }
}