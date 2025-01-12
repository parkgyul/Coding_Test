import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int i = 0; i < n; i++) {
            int taskTime = Integer.parseInt(br.readLine());
            minHeap.add(taskTime);
        }
        
        int totalCost = 0;
        
        while (minHeap.size() > 1) {
            int first = minHeap.poll();
            int second = minHeap.poll();
            
            int mergedCost = first + second;
            totalCost += mergedCost;
            
            minHeap.add(mergedCost);
        }
        
        System.out.println(totalCost);
    }
}