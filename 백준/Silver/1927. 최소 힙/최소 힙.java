import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.util.StringTokenizer;

import java.util.PriorityQueue;

class Main{
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        // 최소 힙(mini-heap)은 PriorityQueue를 의미.
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0; i<n; i++){
            int num = Integer.parseInt(br.readLine());

            if(num == 0){
                if(pq.isEmpty()){
                    sb.append(0).append("\n");
                }else{
                    sb.append(pq.poll()).append("\n");
                }
            }else{
                pq.add(num);
            }
        }

        System.out.print(sb);
    }
}