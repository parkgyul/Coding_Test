import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        List<Point> list = new ArrayList<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            int y = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            
            list.add(new Point(x1, y, 1, i));
            list.add(new Point(x2, y, -1, i));
        }

        
        boolean[] ended = new boolean[N];
        boolean[] visible = new boolean[N];

        int cnt = 0;

        Collections.sort(list);

        for(Point p : list){
            if(p.v == 1){
                pq.add(new Node(p.y, p.index));
            }else{
                ended[p.index] = true;
            }

            while(!pq.isEmpty() && ended[pq.peek().index]) pq.poll();

            if(!pq.isEmpty()){
                int topIdx = pq.peek().index;
                if(!visible[topIdx]){
                    visible[topIdx] = true;
                    cnt++;
                }
            }
        }

        System.out.print(cnt);
    }

    static class Node implements Comparable<Node>{
        int y, index;

        Node(int y,  int index){
            this.y = y;
            this.index = index;
        }

        public int compareTo(Node o){
            return this.y - o.y;
        }
    }

    static class Point implements Comparable<Point>{
        int x, y, v, index;
        
        Point(int x, int y, int v, int index){
            this.x = x;
            this.y = y;
            this.v = v;
            this.index = index;
        }

        public int compareTo(Point o){
            return this.x - o.x;
        }
    }
}