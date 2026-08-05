import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int p = 0;

        List<Point> list = new ArrayList<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            int move = Integer.parseInt(st.nextToken());
            char ch = st.nextToken().charAt(0);

            int nextP = p + (ch == 'R' ? 1 : -1) * move;
            
            list.add(new Point(p, (p < nextP? 1: -1), i));
            list.add(new Point(nextP, (p < nextP? -1: 1), i));

            p = nextP;
        }

        Collections.sort(list);

        Set<Integer> set = new HashSet<>();
        int start = 0;

        int sum = 0;
        for(Point point : list){

            if(point.v == 1){
                set.add(point.index);
                if(set.size() == K){
                    start = point.n;
                }
            }else{
                set.remove(point.index);

                if(set.size() == K-1){
                    sum += (point.n - start);
                }
            }

        }

        System.out.print(sum);


    }

    static class Point implements Comparable<Point>{
        int n, v, index;

        Point(int n, int v, int index){
            this.n = n;
            this.v = v;
            this.index = index;
        }

        public int compareTo(Point o){
            return this.n - o.n;
        }
    }
}