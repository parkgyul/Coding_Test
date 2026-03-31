import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static Stack<Character> stack;
    static int N;
    static StringBuilder result = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++){
            N = Integer.parseInt(br.readLine());

            stack = new Stack<>();

            dfs(1, "1");
            result.append("\n");
        }

        System.out.print(result);

    }

    public static void dfs(int depth, String expr){
        if(depth == N){
            if(calc(expr) == 0){
                result.append(expr).append("\n");
            }

            return;
        }

        dfs(depth+1, expr + ' ' + (depth+1));
        dfs(depth+1, expr + '+' + (depth+1));
        dfs(depth+1, expr + '-' + (depth+1));

    }

    public static int calc(String str) {
        str = str.replace(" ", "");

        int sum = 0;
        int num = 0;
        char op = '+';

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if(Character.isDigit(ch)){
                num = num * 10 + (ch -'0');
            }

            if(!Character.isDigit(ch) || i == str.length() -1){
                if(op == '+') sum += num;
                else if(op == '-') sum -= num;

                op = ch;
                num = 0;
            }
        }

        return sum;

    }
}