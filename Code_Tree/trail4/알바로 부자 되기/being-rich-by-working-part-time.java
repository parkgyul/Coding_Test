import java.io.*; 
import java.util.*; 

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<Info> list = new ArrayList<>();
        int[] dp = new int[N];

        StringTokenizer st;
        for(int i =0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int money = Integer.parseInt(st.nextToken());

            list.add(new Info(start, end, money));
        }

        Collections.sort(list, (a, b)->{
            if(a.e == b.e) return b.s - a.s;
            return a.e - b.e;
        });

        for(int i =0 ; i < N; i++){
            dp[i] = list.get(i).m;
        }

        int answer = 0;

        for(int i = 0; i < N; i++){
            Info a = list.get(i);
            for(int j = 0; j < i; j++){
                Info b = list.get(j);
                if(a.s > b.e){
                    dp[i] = Math.max(dp[i], dp[j] + a.m);
                }
            }

            answer = Math.max(answer, dp[i]);
        }

        System.out.print(answer);
    }

    static class Info{
        int s, e, m;
        Info(int s, int e, int m){
            this.s = s;
            this.e = e;
            this.m = m;
        }
    }
}