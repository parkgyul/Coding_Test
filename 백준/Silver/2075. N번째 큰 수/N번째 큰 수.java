import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.util.StringTokenizer;

import java.util.PriorityQueue;

class Main{
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Long> pq = new PriorityQueue<>();

        for(int i = 0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            while(st.hasMoreTokens()){
                Long num = Long.parseLong(st.nextToken());

                if(pq.size() <n || num > pq.peek()){ // 자리가 있는 경우 or 이번에 들어온 수가 더 크다면
                    pq.add(num);
                }
                
                if(pq.size() >n){
                    pq.poll();
                }
            }
        }
        
        System.out.print(pq.peek());
    }
}