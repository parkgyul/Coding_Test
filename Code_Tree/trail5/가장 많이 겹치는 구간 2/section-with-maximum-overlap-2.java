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

            list.add(new Point(x1, 1, i));
            list.add(new Point(x2, -1, i));
        }

        Set<Integer> set = new HashSet<>();
        Collections.sort(list);

        int sum = 0;

        for(int i = 0; i < 2*N; i++){
            Point p = list.get(i);

            if(p.v == 1){
                set.add(p.index);
            }else{
                if(sum < set.size()){
                    sum = set.size();
                }
                set.remove(p.index);
            }
        }

        System.out.print(sum);
    }

    static class Point implements Comparable<Point>{
        int x, v, index;

        Point(int x, int v, int index){
            this.x = x;
            this.v = v;
            this.index = index;
        }

        public int compareTo(Point o){
            if(this.x == o.x) return o.v - this.v; 
            return this.x - o.x;
        }
    }
}