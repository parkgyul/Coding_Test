import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i =0 ; i < T; i++){
            int N = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> map = new TreeMap<>();
            for(int j = 0; j < N; j++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                char ch = st.nextToken().charAt(0);
                int num = Integer.parseInt(st.nextToken());

                if(ch == 'I'){
                    map.put(num, map.getOrDefault(num, 0) +1);
                }else if(ch == 'D'){
                    if(map.isEmpty()) continue;

                    if(num == 1){ // 최대 삭제
                        int max = map.lastKey();
                        if(map.get(max) == 1) map.remove(max);
                        else map.put(max, map.get(max)-1);
                    }else{ // 최소 삭제
                        int min = map.firstKey();
                        if(map.get(min) == 1) map.remove(min);
                        else map.put(min, map.get(min) -1);
                    }
                }
            }

            if(map.isEmpty())
                sb.append("EMPTY\n");
            else
                sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
        }

        System.out.print(sb);
    }
}