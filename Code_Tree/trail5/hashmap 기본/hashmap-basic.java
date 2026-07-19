import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            int k = Integer.parseInt(st.nextToken());

            switch(str){
                case "add" : {
                    int v = Integer.parseInt(st.nextToken());
                    map.put(k, v);
                    break;
                }
                case "find" : {
                    Integer m = map.get(k);
                    if(m != null){
                        sb.append(m).append("\n");
                    }else{
                        sb.append("None\n");
                    }
                    break;
                }
                case "remove" : {
                    map.remove(k);
                    break;
                }
            }

        }

        System.out.print(sb);
    }
}