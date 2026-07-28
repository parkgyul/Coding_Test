import java.io.*; 
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

       TreeSet<Long> set = new TreeSet<>();

       for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int vel = Integer.parseInt(st.nextToken());
            long end = start + vel * (long)T;

            if(set.ceiling(end) == null){
                set.add(end);
            }else{
                while(set.ceiling(end) != null){
                    set.remove(set.ceiling(end));
                }

                set.add(end);
            }
       }

       System.out.print(set.size());

    }


}