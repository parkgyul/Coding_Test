import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();

            if(str.equals("push")){
                pq.add(Integer.parseInt(st.nextToken()));
            }else if(str.equals("size")){
                sb.append(pq.size()).append("\n");
            }else if(str.equals("empty")){
                sb.append(pq.isEmpty()? 1 : 0).append("\n");
            }else if(str.equals("pop")){
                sb.append(pq.poll()).append("\n");
            }else{
                sb.append(pq.peek()).append("\n");
            }
        }

        System.out.print(sb);
    }
}