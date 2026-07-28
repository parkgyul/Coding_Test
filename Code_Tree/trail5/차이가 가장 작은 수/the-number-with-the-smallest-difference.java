import java.io.*; 
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
    
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(br.readLine());

            set.add(num);
        }

        int min = Integer.MAX_VALUE;

        for(int x : set){
            if(set.ceiling(x+M) != null){
                min = Math.min(min, set.ceiling(x+M) - x);
            }
        }

        System.out.print(min == Integer.MAX_VALUE ? -1 : min);
    }
}