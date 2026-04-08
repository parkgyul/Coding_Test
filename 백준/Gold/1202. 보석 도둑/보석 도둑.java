import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    static int[] bags;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Jewel[] jewels = new Jewel[N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            jewels[i] = (new Jewel(M, V));
        }

        bags = new int[K];
        for(int i = 0; i <K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(jewels, (a, b) -> a.weight - b.weight);
        Arrays.sort(bags);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        long result = 0;
        int j = 0;

        for(int i = 0; i < K; i++){
            int capacity = bags[i];

            while(j < N && jewels[j].weight <= capacity){
                pq.add(jewels[j].value);
                j++;
            }

            if(!pq.isEmpty()){
                result += pq.poll();
            }
        }


        System.out.print(result);
    }

    public static class Jewel {
        int weight, value;

        public Jewel(int weight, int value){
            this.weight = weight;
            this.value = value;
        }
    }
}