import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        TreeMap<Integer, Integer> map = new TreeMap<>();

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();

            if(str.equals("add")){
                int k = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                map.put(k, v);
            }else if(str.equals("find")){
                int k = Integer.parseInt(st.nextToken());
                if(map.containsKey(k)){
                    sb.append(map.get(k)).append("\n");
                }else{
                    sb.append("None").append("\n");
                }
            }else if(str.equals("remove")){
                int k = Integer.parseInt(st.nextToken());
                map.remove(k);
            }else{
                if(map.isEmpty()){
                    sb.append("None");
                }else{
                    for(int k :  map.keySet()){
                        sb.append(map.get(k)+ " ");
                    }
                }
                sb.append("\n");
            }
        }

        System.out.print(sb);
    }
}