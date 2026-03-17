import java.io.*;
import java.util.*;

class Solution {
    static StringBuilder sb;
    static Stack<Character> st;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        for(int test_case = 1; test_case <= 10; test_case++) {
            result.append("#").append(test_case).append(" ");
            int N = Integer.parseInt(br.readLine());

            st = new Stack<>();
            sb = new StringBuilder();

            String str = br.readLine();

            for(int i = 0; i < N; i++){
                char ch = str.charAt(i);

                // 숫자
                if(Character.isDigit(ch)){
                    sb.append(ch);
                }

                // 여는 괄호
                else if(ch == '('){
                    st.push(ch);
                }

                // 닫는 괄호
                else if(ch == ')'){
                    while(st.peek() != '('){
                        sb.append(st.pop());
                    }
                    st.pop();
                }

                // 연산자
                else {
                    while(!st.isEmpty() && priority(st.peek()) >= priority(ch)){
                        sb.append(st.pop());
                    }
                    st.push(ch);
                }
            }

            // 남은 연산자 처리
            while(!st.isEmpty()){
                sb.append(st.pop());
            }
            
            result.append(caculate()).append("\n");
        }

        
        System.out.print(result);
        
    }
    
    public static int caculate(){
        Stack<Integer> s = new Stack<>();
        for(int i = 0; i < sb.length(); i ++) {
            char ch = sb.charAt(i);
            if(Character.isDigit(ch)) {
                s.push(ch - '0' );
                continue;
            }
            int n1 = s.pop();
            int n2 = s.pop();
            if( ch == '+' ){
                s.push(n1 + n2);
            } else if (ch == '*'){
                s.push(n1*n2);
            }
        }
        return s.pop();
    }

    static int priority(char op){
        if(op == '*') return 2;
        if(op == '+') return 1;
        return 0;
    }
}