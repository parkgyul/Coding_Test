import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        TreeMap<Integer, Integer> map = new TreeMap<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            int num = Integer.parseInt(st.nextToken());
            if(map.containsKey(num)) continue;

            map.put(num, i);
        }

        StringBuilder sb = new StringBuilder();

        for(int n : map.keySet()){
            sb.append(n + " " + map.get(n)).append("\n");
        }

        System.out.print(sb);
    }
}