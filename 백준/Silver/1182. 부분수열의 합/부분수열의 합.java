import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

public class Main{
    static boolean[] visited;
    static int[] arr;
    static int cnt;
    static int n, s;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        visited = new boolean[n];
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        back(0, 0);

        if(s == 0) cnt--;

        System.out.println(cnt);
    }

    public static void back(int depth, int sum){
        if(depth == n){
            if(sum == s){
                cnt ++;
            }
            return;
        }
        back(depth+1, sum + arr[depth]);
        back(depth+1, sum);
    }
}