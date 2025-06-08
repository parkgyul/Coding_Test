import java.io.IOException;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String s = br.readLine();
        Stack<Character> stack = new Stack<>();

        boolean flag = false;

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '<'){
                flag = true;
                while(!stack.empty()){
                    sb.append(stack.pop());
                }
                sb.append(c);
            }
            else if(c == '>'){
                flag = false;
                sb.append(c);
            }
            else if(flag){
                sb.append(c);
            }
            else if(!flag){
                if(c == ' '){
                    while(!stack.empty()){
                        sb.append(stack.pop());
                    }
                    sb.append(c);
                }
                else{
                    stack.push(c);
                }
            }
        }
        while(!stack.empty()){
            sb.append(stack.pop());
        }
        System.out.println(sb);
    }
}