import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(st.nextToken());
            pq.add(num);
            if(i < 2){
                sb.append("-1\n");
                continue;
            }

            int a = pq.poll();
            int b = pq.poll();
            int c = pq.poll();

            pq.add(a);
            pq.add(b);
            pq.add(c);

            sb.append((long)a*b*c).append("\n");
        }

        System.out.print(sb);

    }
}