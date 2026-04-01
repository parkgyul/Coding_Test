import java.io.*;
import java.util.*;

public class Main {
    static int L, C;
    static List<Character> c;
    static StringBuilder sb = new StringBuilder();
    static char[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        c = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            c.add(st.nextToken().charAt(0));
        }

        Collections.sort(c);
        result = new char[L];

        dfs(0, 0, 0);

        System.out.print(sb);
    }

    static void dfs(int depth, int start, int moCnt) {
        if (depth == L) {
            if (moCnt >= 1 && (L - moCnt) >= 2) {
                sb.append(result).append("\n");
            }
            return;
        }

        for (int i = start; i < C; i++) {
            char ch = c.get(i);

            result[depth] = ch;

            if (isVowel(ch)) {
                dfs(depth + 1, i + 1, moCnt + 1);
            } else {
                dfs(depth + 1, i + 1, moCnt);
            }
        }
    }

    static boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
}