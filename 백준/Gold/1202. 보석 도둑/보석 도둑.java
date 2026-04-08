import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Jewel[] jewels = new Jewel[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewels[i] = new Jewel(w, v);
        }

        int[] bags = new int[K];
        for(int i =0 ; i < K; i++){
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(jewels, (a, b) -> a.weight - b.weight);
        Arrays.sort(bags);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int j = 0;
        long result = 0;

        for(int i = 0; i < K; i++){
            int capacity = bags[i];

            while(j < N && capacity >= jewels[j].weight){
                pq.add(jewels[j].value);
                j++;
            }

            if(!pq.isEmpty()){
                result += pq.poll();
            }
        }

        System.out.print(result);
    }

    public static class Jewel{
        int weight, value;

        public Jewel(int weight, int value){
            this.weight = weight;
            this.value = value;
        }
    }
}