import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        PriorityQueue<Tuple> pq = new PriorityQueue<>();
        PriorityQueue<Info> wait = new PriorityQueue<>();
        
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            pq.add(new Tuple(i, a, s));
        }

        int max = 0;

        int time = pq.peek().arrival;

        for(int i = 0; i < N; i++){
            Tuple top = pq.poll();

            if(top.arrival > time) time = top.arrival;
            wait.add(new Info(top.num, top.arrival, top.stay));

            if(!pq.isEmpty() && pq.peek().arrival <= time){
                continue;
            }

            while(!wait.isEmpty()){
                Info w = wait.poll();

                max = Math.max(time-w.arrival, max);

                time += w.stay;

                if(!pq.isEmpty() && pq.peek().arrival <= time){
                    break;
                }

            }

        }

        System.out.print(max);

    }

    static class Tuple implements Comparable<Tuple>{
        int num, arrival, stay;

        Tuple(int num, int arrival, int stay){
            this.num = num;
            this.arrival = arrival;
            this.stay = stay;
        }

        public int compareTo(Tuple o){
            if(this.arrival != o.arrival){
                return this.arrival - o.arrival;
            }else{
                return this.num - o.num;
            }
        }
    }

    static class Info implements Comparable<Info>{
        int num, arrival, stay;

        Info(int num, int arrival, int stay){
            this.num = num;
            this.arrival = arrival;
            this.stay = stay;
        }

        public int compareTo(Info o){
            return this.num - o.num;
        }
    }
}