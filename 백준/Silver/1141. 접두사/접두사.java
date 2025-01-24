import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayList<String> words = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            words.add(br.readLine());
        }

        Collections.sort(words, new Comparator<String>() {
            public int compare(String e1, String e2) {
                return e1.compareTo(e2);
            }
        });

        int maxSize = 1;
        int currentSize = 1;

        for (int i = 1; i < words.size(); i++) {
            if (!words.get(i).startsWith(words.get(i - 1))) {
                currentSize = Math.max(currentSize + 1, maxSize);
                maxSize = currentSize;
            }
        }

        System.out.println(maxSize);
    }
}