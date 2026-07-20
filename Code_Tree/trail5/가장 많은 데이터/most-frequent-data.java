import java.io.*;
import java.util.*; 

public class Main {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Map<String, Integer> map = new HashMap<>();
        int max = 0;
        
        for(int i = 0; i < N; i++){
            String str = br.readLine();
            map.put(str, map.getOrDefault(str, 0) + 1);

            max = Math.max(max, map.get(str));
        }

        System.out.println(max);
    }
}