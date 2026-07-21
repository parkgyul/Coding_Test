import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        HashSet<Integer> set = new HashSet<>();

        StringTokenizer st; 

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i <N; i++){
            int num = Integer.parseInt(st.nextToken());
            set.add(num);
        }

        System.out.print(set.size());
    }
}