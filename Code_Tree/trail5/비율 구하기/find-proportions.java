import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        TreeMap<String, Integer> map = new TreeMap<>();

        for(int i = 0; i < N; i++){
            String str = br.readLine();
            map.put(str, map.getOrDefault(str, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        for(String s : map.keySet()){
            double value = map.get(s)/(double) N * 100;
            sb.append(s).append(" ").append(String.format("%.4f", value)).append("\n");
        }
        System.out.print(sb);
    }
}