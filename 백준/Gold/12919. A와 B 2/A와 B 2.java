import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main{
    static String S, T;
    static boolean flag = false;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = br.readLine();
        T = br.readLine();
        dfs(T);
        System.out.print(flag ? 1 : 0);
    }

    public static void dfs(String str) {
        if(flag) return;

        if (str.length() == S.length()) {
            if (str.equals(S)) {
                flag = true;
            }
            return;
        }

        if (str.length() < S.length())
            return;


        if (str.charAt(str.length() - 1) == 'A')
            dfs(str.substring(0, str.length() - 1));

        if (str.charAt(0) == 'B') {
            String next = new StringBuilder(str.substring(1)).reverse().toString();
            dfs(next);
        }
    }
}