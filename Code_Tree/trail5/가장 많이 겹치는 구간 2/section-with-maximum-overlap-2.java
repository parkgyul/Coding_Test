import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        List<Point> list = new ArrayList<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            list.add(new Point(x1, 1));
            list.add(new Point(x2, -1));
        }

        Set<Integer> set = new HashSet<>();
        Collections.sort(list);

        int sum = 0;
        int max = 0;

        for(int i = 0; i < 2*N; i++){
            Point p = list.get(i);

            sum += p.v;

            if(sum > max){
                max = sum;
            }
        }

        System.out.print(max);
    }

    static class Point implements Comparable<Point>{
        int x, v;

        Point(int x, int v){
            this.x = x;
            this.v = v;
        }

        public int compareTo(Point o){
            if(this.x == o.x) return o.v - this.v; 
            return this.x - o.x;
        }
    }
}