import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    static int L, C;
    static List<Character> c;
    static StringBuilder sb = new StringBuilder();
    static char[] result;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        c = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < C; i++){
            char ch = st.nextToken().charAt(0);

            c.add(ch);
        }

        Collections.sort(c);

        for(int i = 0; i < C-L+1; i++){
            result = new char[L];
            int cnt = 0;
            char ch = c.get(i);
            if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                cnt++;
            }
            result[0] = ch;
            dfs(1, i, cnt);
        }

        System.out.print(sb);
    }

    public static void dfs(int depth, int index, int moCnt){
        if(depth == L){
            if(moCnt >= 1 && L - moCnt >= 2){
                for(int i = 0; i < L; i++){
                    sb.append(result[i]);
                }
                sb.append("\n");
            }
            return;
        }

        for(int i = index+1; i < C; i++){
            char ch = c.get(i);
            if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                if(L - moCnt < 2)
                    continue;

                result[depth] = ch;
                dfs(depth+1, i, moCnt+1);
                result[depth] = ' ';
            }else{
                result[depth] = ch;
                dfs(depth+1, i, moCnt);
                result[depth] = ' ';
            }
        }
    }
}