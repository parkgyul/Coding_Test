import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashSet<Integer> set = new HashSet<>();

        StringBuilder sb= new StringBuilder();

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i <N; i++){
            int num = Integer.parseInt(st.nextToken());

            set.add(num);
        }
        int cnt =0;
        
        st = new StringTokenizer(br.readLine());
        for(int j = 0; j < M; j++){
            int num = Integer.parseInt(st.nextToken());
            if(set.contains(num)) cnt += 2;
        }

        System.out.print(N+M-cnt);
    }
}