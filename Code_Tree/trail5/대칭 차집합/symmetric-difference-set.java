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

        
        st = new StringTokenizer(br.readLine());
        for(int j = 0; j < M; j++){
            int num = Integer.parseInt(st.nextToken());
            if(set.contains(num)){
                set.remove(num);
            }else{
                set.add(num);
            }
        }

        System.out.print(set.size());
    }
}