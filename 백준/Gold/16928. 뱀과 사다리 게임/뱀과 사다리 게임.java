import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
public class Main{
    static Map<Integer, Integer> snakes;
    static Map<Integer, Integer> ladders;
    static boolean[] visited;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());


        ladders = new HashMap<>();
        snakes = new HashMap<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            ladders.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            snakes.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        visited = new boolean[101];

        System.out.print(bfs());

    }

    public static int bfs(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        Integer ladder = ladders.get(1);
        if (ladder != null) {
            pq.add(new Node(ladder, 0));
        }

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int cur_loc = cur.loc;
            int cur_cnt = cur.cnt;

            if (visited[cur_loc])
                continue;


            if (cur_loc == 100) {
                return cur_cnt;
            }


            visited[cur_loc] = true;

            for (int i = 1; i <= 6; i++) {
                int next_loc = cur_loc + i;

                if (next_loc > 100) break;

                if (visited[next_loc]) continue;

                // 뱀이 있는 경우
                Integer snake = snakes.get(next_loc);

                if (snake != null) {
                    next_loc = snake;
                }

                // 사다리 있는 경우
                ladder = ladders.get(next_loc);
                if (ladder != null) {
                    if (visited[ladder])
                        continue;

                    pq.add(new Node(ladder, cur_cnt+1));
                }

                pq.add(new Node(next_loc, cur_cnt+1));
            }
        }

        return -1;
    }

    public static class Node implements Comparable<Node>{
        int loc, cnt;

        public Node(int loc, int cnt){
            this.loc = loc;
            this.cnt = cnt;
        }

        public int compareTo(Node o){
            return this.cnt - o.cnt;
        }
    }

}