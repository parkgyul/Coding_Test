import java.io.BufferedReader; 
import java.io.InputStreamReader;

import java.util.Stack;
class Solution
{
    static Stack<Character> st;
	public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= 10; test_case++)
		{
            sb.append("#").append(test_case).append(" ");
            
            int l = Integer.parseInt(br.readLine());
            String str = br.readLine();
            
           st = new Stack<>();
           for(int i = 0; i <l; i++){
                char ch = str.charAt(i);
                match(ch);
            }
          
            if(st.isEmpty()) sb.append("1").append("\n");
            else sb.append("0").append("\n");
		}
        System.out.print(sb);
	}
    
    public static void match(char ch1){
        
        if (st.isEmpty()) {
            st.push(ch1);
            return;
        }
        char ch2 = st.peek();

        if(ch1 == '}' && ch2 == '{'){
            st.pop();
            return;
        }
        else  if(ch1 == ']' && ch2 == '['){
            st.pop();
            return;
        }
        else  if(ch1 == ')' && ch2 == '('){
            st.pop();
            return;
        }
        else  if(ch1 == '>' && ch2 == '<'){
            st.pop();
            return;
        } else{
           st.push(ch1);
        }
    }
}