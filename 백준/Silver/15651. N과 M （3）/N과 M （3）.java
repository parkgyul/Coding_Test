import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
    static int n,m;
    static int[] arr;
    static boolean[] visited;
    static StringBuilder result ;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //n까지의 수중에서
        n = Integer.parseInt(st.nextToken());
        //길이가 m인 수열을 구하자
        m = Integer.parseInt(st.nextToken());

        arr = new int[m];
        visited = new boolean[n];
        result = new StringBuilder();

        dfs(0);
        System.out.println(result);
    }
    public static void dfs(int depth){
        if(depth == m){
            for(int i = 0; i<m; i++){
                result.append(arr[i]).append(' ');
            }
            result.append("\n");
            return;
        }
        
        for(int i = 0; i<n; i++){
            arr[depth] = i+1;
            dfs(depth+1);
        }
    }
}