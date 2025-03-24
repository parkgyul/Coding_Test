import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Queue;
import java.util.LinkedList;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        Queue<Integer> q = new LinkedList<>();

        for(int i = 1; i<= num; i++){
            q.offer(i);
        }
        while(q.size() > 1){
            q.poll();
            int temp = q.poll();
            q.offer(temp);
        }
        System.out.println(q.poll());

    }
}