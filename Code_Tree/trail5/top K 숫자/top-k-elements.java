import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    // 변수 선언
    public static int n, k;
    public static TreeSet<Integer> s = new TreeSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        k = sc.nextInt();
        
        // 입력받은 수들을 treeset에 넣어줍니다.
        for(int i = 0; i < n; i++) {
            int x = sc.nextInt();
            s.add(-x);
        }

        // cnt : 출력한 숫자의 개수
        int cnt = 0;

        // iterator를 이용해 가장 큰 k개의 숫자를 출력합니다.
        for(int num : s) {
            System.out.print(-num + " ");
            cnt++;

            if(cnt == k)
                break;
        }
    }
}
