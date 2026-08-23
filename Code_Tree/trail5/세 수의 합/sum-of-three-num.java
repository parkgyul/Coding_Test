import java.util.Scanner;
import java.util.HashMap;

public class Main {
    public static final int MAX_N = 5000;
    
    // 변수 선언
    public static int n, k;
    public static int[] arr = new int[MAX_N+1];
    public static HashMap<Integer, Integer> freq = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        k = sc.nextInt();

        int ans = 0;

        // 각 숫자가 몇 번씩 나왔는지를
        // hashmap에 기록해줍니다.
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            if(!freq.containsKey(arr[i]))
                freq.put(arr[i], 1);
            else
                freq.put(arr[i], freq.get(arr[i]) + 1);
        }

        // 배열을 앞에서부터 순회하며 쌍을 만들어줍니다.
        for(int i = 0; i < n; i++) {
            // 이미 순회한 적이 있는 숫자는 빼 버림으로서
            // 같은 조합이 여러번 세어지는 걸 방지합니다.
            if(freq.containsKey(arr[i]))
                freq.put(arr[i], freq.get(arr[i]) - 1);

            for(int j = 0; j < i; j++) {
                // 전처리를 해주었기 때문에 이미 순회한 적 있는 값은 hashmap에 없습니다.
                // 이와 같은 방식으로 같은 조합이 중복되어 세어지는 걸 방지할 수 있습니다.
                if(freq.containsKey(k - arr[i] - arr[j]))
                    ans += freq.get(k - arr[i] - arr[j]);
            }
        }

        System.out.print(ans);
    }
}
