import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        List<Point> list = new ArrayList<>();


        for(int i = 0; i< N; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            list.add(new Point(x1, 1, i));
            list.add(new Point(x2, -1, i));
        }

        Collections.sort(list);

        Set<Integer> set = new HashSet<>();
        PriorityQueue<Integer> waiting = new PriorityQueue<>();

        int[] computers = new int[N];

        int comp = 0;

        for(Point p : list){
            if(p.v == 1){
                if(waiting.isEmpty()){
                    computers[p.index] = ++comp;
                }else{
                    computers[p.index] = waiting.poll();
                }

                set.add(p.index);
            }else{
                set.remove(p.index);
                waiting.add(computers[p.index]);
            }
        }

        StringBuilder sb = new StringBuilder();
        
        for(int c: computers){
            sb.append(c).append(" ");
        }

        System.out.print(sb); 

    }

    static class Point implements Comparable<Point>{
        int n, v, index;

        Point(int n, int v, int index){
            this.n = n;
            this.v = v;
            this.index = index;
        }

        public int compareTo(Point o){
            if(this.n == o.n){
                return o.v - this.v;
            }

            return this.n - o.n;
        }

    }
}