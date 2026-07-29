import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            if(Math.abs(a) == Math.abs(b)) return a - b;
            return Math.abs(a) - Math.abs(b);
        });

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(br.readLine());
            if(num != 0){
                pq.add(num);
            }else{
                sb.append(pq.isEmpty() ? 0 : pq.poll()).append("\n");
            }
        }

        System.out.print(sb);

    }
}