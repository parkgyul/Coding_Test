import java.io.*; 
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        TreeSet<Node> set = new TreeSet<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            set.add(new Node(x, y));
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            Node result = set.ceiling(new Node(x, y));
            if(result == null){
                sb.append("-1 -1\n");
            }else{
                sb.append(result.x + " " + result.y).append("\n");
            }
        }

        System.out.print(sb);


    }
    static class Node implements Comparable<Node>{
        int x, y;

        Node(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int compareTo(Node o){
            if(this.x == o.x) return this.y -o.y;
            return this.x - o.x;
        }
    }
}