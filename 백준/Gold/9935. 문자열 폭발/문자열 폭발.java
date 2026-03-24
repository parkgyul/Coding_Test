import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String word = br.readLine();

        Stack<Character> stack = new Stack<>();

        for(int i =0 ; i < str.length(); i++){
            stack.push(str.charAt(i));

            if(stack.size() >= word.length()){
                boolean match = true;
                for(int j = 0; j < word.length(); j++){
                    if(stack.get(stack.size() - word.length() + j) != word.charAt(j)){
                        match = false;
                        break;
                    }
                }

                if(match){
                    for(int j = 0; j < word.length(); j++){
                        stack.pop();
                    }
                }
            }
        }

        if(stack.isEmpty()){
            System.out.println("FRULA");
        }else {
            StringBuilder sb = new StringBuilder();
            for(char c : stack) sb.append(c);
            System.out.println(sb);
        }
    }
}