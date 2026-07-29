import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Point> pq = new PriorityQueue<>();

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            pq.add(new Point(x, y));
        }

        for(int i = 0; i < M; i++){
            Point p = pq.poll();
            pq.add(new Point(p.x+2, p.y +2));
        }

        System.out.println(pq.peek().x + " " + pq.peek().y);
    }

    static class Point implements Comparable<Point>{
        int x, y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int compareTo(Point o){
            if((this.x + this.y) != (o.x + o.y)) return (this.x + this.y) - (o.x + o.y);
            if(this.x == o.x) return this.y - o.y;
            return this.x - o.x;
        }
    }
}