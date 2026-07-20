import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()); 

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<String, String> map= new HashMap<>();
        for(int i = 1; i <= N; i++){
            String str = br.readLine();

            map.put(str, Integer.toString(i));
            map.put(Integer.toString(i), str);
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= M; i++){
            String str = br.readLine();

            sb.append(map.get(str)).append("\n");
        }

        System.out.print(sb);
    }
}