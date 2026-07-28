import java.io.*; 
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while(T-- > 0){
            int N = Integer.parseInt(br.readLine());
            
            TreeSet<Integer> set = new TreeSet<>();
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());

                char ch = st.nextToken().charAt(0);
                int num = Integer.parseInt(st.nextToken());

                if(ch == 'I'){
                    set.add(num);
                }else{
                    if(set.isEmpty()) continue;
                    if(num == 1) set.remove(set.last());
                    else set.remove(set.first());
                }

            }

            sb.append(set.isEmpty() ? "EMPTY" : set.last() + " " + set.first()).append("\n");
        }

        System.out.print(sb);

    

        
    }
}