import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int answer = 0;

        long[] sums = new long[N + 1];
        int[] first = new int[7];

        Arrays.fill(first, -1);
        first[0] = 0;

        for (int i = 1; i <= N; i++) {
            sums[i] = sums[i - 1] + Integer.parseInt(br.readLine());

            int occur = (int) (sums[i] % 7);

            if(first[occur] != -1) answer = Math.max(answer, i - first[occur]);
            else first[occur] = i;
        }

        System.out.print(answer);
    }
}
