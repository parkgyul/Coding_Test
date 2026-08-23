import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long K = Long.parseLong(st.nextToken());

        Map<Long, Long> map = new HashMap<>();

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            long num = Long.parseLong(st.nextToken());
            map.put(num, map.getOrDefault(num, 0L) + 1);
        }

        List<Long> list = new ArrayList<>(map.keySet());
        Collections.sort(list);

        long answer = 0;

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {

                long a = list.get(i);
                long b = list.get(j);
                long c = K - a - b;

                if (!map.containsKey(c)) {
                    continue;
                }

                // c가 b보다 작거나 같으면 이미 다른 경우에서 처리됨
                if (c <= b) {
                    continue;
                }

                long countA = map.get(a);
                long countB = map.get(b);
                long countC = map.get(c);

                answer += countA * countB * countC;
            }
        }

        // a = b인 경우
        for (int i = 0; i < list.size(); i++) {

            long a = list.get(i);
            long b = K - 2 * a;

            if (b <= a) {
                continue;
            }

            if (!map.containsKey(b)) {
                continue;
            }

            long countA = map.get(a);

            if (countA >= 2) {
                answer += countA * (countA - 1) / 2 * map.get(b);
            }
        }

        // a < b = c인 경우
        for (int i = 0; i < list.size(); i++) {

            long b = list.get(i);
            long a = K - 2 * b;

            if (a >= b) {
                continue;
            }

            if (!map.containsKey(a)) {
                continue;
            }

            long countB = map.get(b);

            if (countB >= 2) {
                answer += map.get(a) * countB * (countB - 1) / 2;
            }
        }

        // a = b = c인 경우
        if (K % 3 == 0) {

            long a = K / 3;

            if (map.containsKey(a)) {

                long count = map.get(a);

                if (count >= 3) {
                    answer += count * (count - 1) * (count - 2) / 6;
                }
            }
        }

        System.out.println(answer);
    }
}