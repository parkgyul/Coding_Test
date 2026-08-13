import java.io.*;
import java.util.*;

public class Main {
    static int N, M, X;

    static int max = Integer.MIN_VALUE;

    static List<Node>[] nodes;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        nodes = new ArrayList[N+1];

        for(int i = 1; i <= N; i++){
            nodes[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            nodes[a].add(new Node(b, c));
        }

        int max = Integer.MIN_VALUE;

        for(int i = 1; i <= N; i++){
            if(i == X) continue;

            max = Math.max(max, bfs(X, i) + bfs(i, X));
        }

        System.out.print(max);
    }

    static int bfs(int start, int target){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        int[] costs = new int[N+1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[start] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(cur.c > costs[cur.n]) continue;

            if(cur.n == target) return cur.c;

            for(Node next : nodes[cur.n]){
                int nextCost = next.c + cur.c;

                if(nextCost >= costs[next.n]) continue;

                costs[next.n] = nextCost;
                pq.add(new Node(next.n, nextCost));
            }
        }

        return -1;
    }

    static class Node implements Comparable<Node>{
        int n, c;
        Node(int n, int c){
            this.n = n;
            this.c = c;
        }

        public int compareTo(Node o){
            return this.c - o.c;
        }
    }
}