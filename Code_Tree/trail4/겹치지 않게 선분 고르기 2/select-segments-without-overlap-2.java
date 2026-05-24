import java.io.*; 
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<int[]> list = new ArrayList<>();

        int[] dp = new int[N];
    
        StringTokenizer st;

        for(int i =0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            list.add(new int[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())});
            dp[i] = 1;
        }

        Collections.sort(list, (a, b) -> {
            if(a[1] == b[1]) return b[0] - a[0];
            return a[1] - b[1];
        });

        int answer = 1;

        for(int i = 1; i < N; i++){
            int[] org = list.get(i);
            for(int j = 0; j < i; j++){
                int[] com = list.get(j);
                if(org[0] > com[1]){
                    dp[i] = Math.max(dp[j] +1, dp[i]);
                }
            }
            answer = Math.max(dp[i], answer);
        }

        System.out.print(answer);
    }
}