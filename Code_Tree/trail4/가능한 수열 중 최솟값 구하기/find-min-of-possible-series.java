import java.io.*;

public class Main {

    static int N;
    static StringBuilder sequence = new StringBuilder();
    static char[] numbers = {'4', '5', '6'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dfs();

        System.out.println(sequence);
    }

    static boolean dfs() {
        if (sequence.length() == N) {
            return true;
        }

        for (char num : numbers) {
            sequence.append(num);

            if (isPossible()) {
                if (dfs()) {
                    return true;
                }
            }

            sequence.deleteCharAt(sequence.length() - 1);
        }

        return false;
    }

    static boolean isPossible() {
        int len = sequence.length();

        for (int size = 1; size <= len / 2; size++) {
            boolean same = true;

            for (int i = 0; i < size; i++) {
                char left = sequence.charAt(len - 2 * size + i);
                char right = sequence.charAt(len - size + i);

                if (left != right) {
                    same = false;
                    break;
                }
            }

            if (same) {
                return false;
            }
        }

        return true;
    }
}