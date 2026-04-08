import java.io.IOException;
import java.util.Arrays;
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

        int[][] jewels = new int[N][2];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            jewels[i][0] = Integer.parseInt(st.nextToken());
            jewels[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] bags = new int[K];
        for(int i =0 ; i < K; i++){
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(jewels, (a, b) -> a[0] - b[0]);
        Arrays.sort(bags);

        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b - a);
        int j = 0;
        long result = 0;

        for(int i = 0; i < K; i++){
            int capacity = bags[i];

            while(j < N && jewels[j][0] <= capacity){
                pq.add(jewels[j][1]);
                j++;
            }

            if(!pq.isEmpty()){
                result += pq.poll();
            }
        }

        System.out.print(result);
    }
}