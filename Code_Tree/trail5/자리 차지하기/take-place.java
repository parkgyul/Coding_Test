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
        for(int i = 0; i < M; i++){
            set.add(i+1);
        }

        st = new StringTokenizer(br.readLine());
        int cnt = 0;
        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(st.nextToken());
            if(set.floor(num) == null) break;

            set.remove(set.floor(num));
            cnt++;
        }

        System.out.print(cnt);
    }
}