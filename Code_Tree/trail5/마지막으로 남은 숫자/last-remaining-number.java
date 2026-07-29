import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(st.nextToken()); 
            pq.add(num);
        }

        while(pq.size() >= 2){
            int a = pq.poll();int b = pq.poll();

            if(Math.abs(a-b) != 0){
                pq.add(Math.abs(a-b));
            }

        }

        System.out.print(pq.isEmpty() ? -1 : pq.poll());

    }
}