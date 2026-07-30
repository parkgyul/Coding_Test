import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

       
        while(T-- > 0){
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            
            int[] arr = new int[N];
            for(int i = 0; i < N; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            
            int median = arr[0];
            sb.append(median + " ");

            PriorityQueue<Integer> minPq = new PriorityQueue<>();
            PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());

            for(int i = 1; i < N; i++){
                if(i % 2 == 1){ //짝수번째
                    if(arr[i] < median){
                        maxPq.add(arr[i]);
                    }else{
                        minPq.add(arr[i]);
                    }
                }else{ //홀수번째
                    int temp;

                    if(maxPq.size() > minPq.size()){
                        temp = maxPq.poll();
                    }else{
                        temp = minPq.poll();
                    }

                    int[] nums = new int[]{median, arr[i], temp};
                    Arrays.sort(nums);

                    maxPq.add(nums[0]);
                    median = nums[1];
                    minPq.add(nums[2]);

                    sb.append(median + " ");
                }
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}