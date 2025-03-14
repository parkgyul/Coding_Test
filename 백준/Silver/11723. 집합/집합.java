import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        Set<Integer> s = new HashSet<>();
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if(command.equals("add")){
                s.add(Integer.parseInt(st.nextToken()));
            }
            else if(command.equals("remove")){
                s.remove(Integer.parseInt(st.nextToken()));
            }
            else if(command.equals("check")){
                if(s.contains(Integer.parseInt(st.nextToken())))
                    sb.append("1\n");
                else
                    sb.append("0\n");
            }
            else if(command.equals("toggle")){
                int num = Integer.parseInt(st.nextToken());
                if(s.contains(num))
                    s.remove(num);
                else
                    s.add(num);
            }
            else if(command.equals("all")){
                for(int j = 1; j<= 20; j++){
                    s.add(j);
                }
            }
            else{
                for(int j = 1; j<= 20; j++){
                    s.remove(j);
                }
            }

        }
        System.out.println(sb);
    }

}