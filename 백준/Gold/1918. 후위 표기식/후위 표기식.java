import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        Stack<Character> st = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for(int i =0 ; i < str.length(); i++){
            char ch = str.charAt(i);

            if(isLetter(ch)) sb.append(ch);

            else if(ch == '('){
                st.push(ch);
            }

            else if(ch == ')'){
                while(!st.isEmpty()){
                    char temp = st.pop();
                    if(temp == '(') break;
                    sb.append(temp);
                }
            }

            else{
                while(!st.isEmpty() && priority(st.peek()) >= priority(ch)){
                    sb.append(st.pop());
                }

                st.push(ch);
            }
        }

        while(!st.isEmpty()){
            sb.append(st.pop());
        }

        System.out.print(sb);
    }

    public static boolean isLetter(char ch){
        return 'A' <= ch && ch <= 'Z';
    }

    public static int priority(char ch){
        if(ch =='+' || ch == '-') return 1;
        else if(ch == '*' || ch == '/') return 2;
        return 0;
    }
}

