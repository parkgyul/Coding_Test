import java.io.*; 
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        TreeSet<Integer> set = new TreeSet<>();

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            String str = st.nextToken();

            if(str.equals("add")){
                int num = Integer.parseInt(st.nextToken());
                set.add(num);
            }else if(str.equals("largest")){
                if(set.isEmpty()){
                    sb.append("None").append("\n");
                }else{
                    sb.append(set.last()).append("\n");
                }
            }else if(str.equals("smallest")){
                if(set.isEmpty()){
                    sb.append("None").append("\n");
                }else{
                    sb.append(set.first()).append("\n");
                }
            }else if(str.equals("lower_bound")){
                int num = Integer.parseInt(st.nextToken());
                sb.append(set.ceiling(num) == null ? "None" : set.ceiling(num) ).append("\n");
            }else if(str.equals("upper_bound")){
                int num = Integer.parseInt(st.nextToken());
                sb.append(set.higher(num) == null ? "None" : set.higher(num) ).append("\n");
            }else if(str.equals("find")){
                int num = Integer.parseInt(st.nextToken());

                sb.append((set.contains(num)) ? "true" : "false").append("\n");
            }else {
                int num = Integer.parseInt(st.nextToken());
                set.remove(num);
            }
        }

        System.out.print(sb);
    }
}