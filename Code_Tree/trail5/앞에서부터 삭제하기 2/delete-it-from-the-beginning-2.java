import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
    
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(st.nextToken()); 
            arr[i] = num;
        }

        pq.add(arr[N-1]);

        double result = 0;
        int sum = 0;
        sum += arr[N-1];

        for(int i = N-2; i >= 0; i--){
            sum += arr[i];

            pq.add(arr[i]);
            double value = (double)(sum - pq.peek())/ (N - i-1);
            if(result < value) result = value;
        }
        

        System.out.print(String.format("%.2f", result));

    }
}