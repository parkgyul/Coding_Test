import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.*;

class Main{
    static int N, M;
    static List<Node>[] nodes;
    static int dest;
    static int[] costs;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 도시의 개수
        M = Integer.parseInt(br.readLine()); // 버스의 개수

        nodes = new ArrayList[N+1];

        for(int i = 1; i < N+1; i++){
            nodes[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            nodes[s].add(new Node(e, c));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        dest = Integer.parseInt(st.nextToken());

        costs = new int[N+1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        bfs(start);

        System.out.print(costs[dest]);
    }
    public static void bfs(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        costs[start] = 0;

        boolean[] visited = new boolean[N+1];

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            int cur_city = cur.city;
            int cur_cost = cur.cost;

            if(visited[cur_city])
                continue;

            visited[cur_city] = true;

            for(Node next : nodes[cur_city]){
                int next_city = next.city;

                int next_cost = cur_cost + next.cost;

                if(next_cost >= costs[next_city])
                    continue;

                costs[next_city] = next_cost;
                pq.add(new Node(next_city, next_cost));
            }
        }

    }

    public static class Node implements Comparable<Node>{
        int city, cost;
        public Node(int city, int cost){
            this.city = city;
            this.cost = cost;
        }

        public int compareTo(Node o){
            return this.cost - o.cost;
        }

    }
}