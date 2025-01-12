import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        
        for (int i = 0; i < n; i++) {
            int taskTime = Integer.parseInt(br.readLine());
            priorityQueue.add(taskTime);
        }
        
        int result = 0;
        
        while (priorityQueue.size() > 1) {
            int first = priorityQueue.poll();
            int second = priorityQueue.poll();
            
            int sum = first + second;
            result += sum;
            
            priorityQueue.add(sum);
        }
        
        System.out.println(result);
    }
}