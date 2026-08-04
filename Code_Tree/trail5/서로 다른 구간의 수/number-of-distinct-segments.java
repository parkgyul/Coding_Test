import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        ArrayList<Node> nodes = new ArrayList<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            nodes.add(new Node(a, 1, i));
            nodes.add(new Node(b, -1, i));
        }

        Collections.sort(nodes);

        HashSet<Integer> set = new HashSet<>();

        int answer = 0;

        for(int i = 0; i < 2*N; i++){
            int x = nodes.get(i).x;
            int v = nodes.get(i).v;
            int index = nodes.get(i).index;

            if(v == 1){
                if(set.size() == 0) answer ++;

                set.add(index);
            }else{
                set.remove(index);
            }
        }

        System.out.print(answer);
    }

    static class Node implements Comparable<Node>{
        int x, v, index;

        Node(int x, int v, int index){
            this.x = x;
            this.v = v;
            this.index = index;
        }

        public int compareTo(Node o){
            return this.x - o.x;
        }
    }
}