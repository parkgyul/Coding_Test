import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(T-- > 0){
           String cmd = br.readLine();
           int n = Integer.parseInt(br.readLine());

           String input = br.readLine();
           input = input.substring(1, input.length()- 1);

            Deque<Integer> dq = new ArrayDeque<>();

            if(n != 0){
                for(String s : input.split(",")){
                    dq.add(Integer.parseInt(s));
                }
            }

            boolean reversed = false;
            boolean flag = true;

            for(char c : cmd.toCharArray()){
                if( c == 'R'){
                    reversed = !reversed;
                }else {
                    if(dq.isEmpty()){
                        sb.append("error\n");
                        flag = false;
                        break;
                    }

                    if(!reversed) dq.pollFirst();
                    else dq.pollLast();
                }
            }

            if(!flag) continue;

            sb.append("[");
            while(!dq.isEmpty()){
                if(!reversed) sb.append(dq.pollFirst());
                else sb.append(dq.pollLast());

                if(!dq.isEmpty()) sb.append(",");
            }
            sb.append("]\n");

        }
        System.out.print(sb);
    }
}