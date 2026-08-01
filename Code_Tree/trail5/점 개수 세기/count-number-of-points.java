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
        int[] arr = new int[N];
        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
        }

        Arrays.sort(arr);

        for(int i = 0; i < N; i++){
            map.put(arr[i], i+1);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine()); 
            int from  = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            Integer fk = map.floorKey(from);
            Integer k1 = (fk == null) ? map.ceilingKey(from) : fk;

            Integer ck = map.ceilingKey(to);
            Integer k2 = (ck == null) ? map.floorKey(to) : ck;

            int offset = (k1 < from ? 1 : 0) + (k2 > to ? 1 : 0); 

            sb.append(map.get(k2) - map.get(k1) + 1 - offset).append("\n");
        }

        System.out.print(sb);
    }
}