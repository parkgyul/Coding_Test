import java.io.*; 
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        
        st = new StringTokenizer(br.readLine());

        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        int min = Integer.MAX_VALUE;

        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(st.nextToken());
            set.add(num);
            Integer high = set.higher(num);
            int low = set.lower(num);

            if(high != null){
               min = Math.min(min, high-num);
            }
            sb.append(min = Math.min(min, num - low)).append("\n");
        }

        System.out.print(sb);
    
    }
}