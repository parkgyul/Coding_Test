import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        HashSet<Integer> set = new HashSet<>();
        StringTokenizer st; 

        StringBuilder sb= new StringBuilder();
        for(int i = 0; i <N; i++){
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();

            int num = Integer.parseInt(st.nextToken());

            if(str.equals("find")){
                sb.append(set.contains(num)).append("\n");
            }else if(str.equals("add")){
                set.add(num);
            }else{
                set.remove(num);
            }
        }

        System.out.print(sb);
    }
}