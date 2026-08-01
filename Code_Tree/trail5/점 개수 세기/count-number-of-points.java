import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        TreeMap<Integer, Integer> map = new TreeMap<>();

        st = new StringTokenizer(br.readLine()); 
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(st.nextToken());
            set.add(num);
        }

        set.add((int)(1000000001));

        int cnt = 1;
        for(int num : set){
            map.put(num, cnt++);
        }

    
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            int newFrom = map.get(set.ceiling(from));
            int newTo = map.get(set.higher(to));

            sb.append(newTo - newFrom).append("\n");
        }
        System.out.print(sb);
    }
}