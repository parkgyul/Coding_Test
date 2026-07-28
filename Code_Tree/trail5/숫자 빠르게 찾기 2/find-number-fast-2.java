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

        st = new StringTokenizer(br.readLine());
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0; i < N; i++){
            set.add(Integer.parseInt(st.nextToken()));
        }

        for(int i = 0; i < M; i++){
            int num = Integer.parseInt(br.readLine());
            if(set.ceiling(num) == null){
                sb.append("-1\n");
            }else{
                sb.append(set.ceiling(num)).append("\n");
            }
        }

        System.out.print(sb);
    }
}