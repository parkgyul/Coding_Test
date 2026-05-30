import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();
        for(int i =0 ; i < N; i++){
            list.add(Integer.parseInt(br.readLine()));
        }

        StringTokenizer st;

        for(int cnt = 0; cnt < 2; cnt++){
             st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken())-1;
            for(int i = 0; i < e-s+1; i++){
                list.remove(s);
            }
        }

        System.out.println(list.size());

        for(int n : list){
            System.out.println(n);
        }

    }
}