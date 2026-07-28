import java.io.*; 
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
    
        TreeSet<Integer> set = new TreeSet<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            set.add(-Integer.parseInt(st.nextToken()));
        }

        int cnt = 0;

        // iterator를 이용해 가장 큰 k개의 숫자를 출력합니다.
        for(int num : set) {
            sb.append(-num + " ");
            cnt++;

            if(cnt == K)
                break;
        }

        System.out.print(sb);
        
    }
}