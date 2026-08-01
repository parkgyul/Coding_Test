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

        int exitTime = 0;

        for(int i = 0; i < N; i++){
            Tuple top = pq.poll();

            while(top.arrival > exitTime && !wait.isEmpty()){
                Info wp = wait.poll();

                max = Math.max(max, exitTime - wp.arrival);

                exitTime += wp.stay;
            }

            if(top.arrival > exitTime){
                exitTime = top.arrival + top.stay;
            }else{
                wait.add(new Info(top.num, top.arrival, top.stay));
            }
        }

        while(!wait.isEmpty()){
            Info wp = wait.poll();

            max = Math.max(max, exitTime - wp.arrival);

            exitTime += wp.stay;
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