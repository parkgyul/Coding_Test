import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class Main{
    static int[] arr;
    static boolean[] visited;
    private static StringBuilder result = new StringBuilder();

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //n까지의 수
        int n = Integer.parseInt(st.nextToken());
        //m 길이 만큼
        int m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        visited = new boolean[n];
        dfs(n,m,0);
        System.out.println(result);
        }

    public static void dfs(int n, int m, int depth) {

        if(depth == m){
            for(int i  = 0; i<m; i++){
                result.append(arr[i]).append(' ');
            }
            result.append('\n');
            return;
        }

        for(int i = 0; i< n; i++){
            //해당 노드를 방문 하지 않았다면
            if(!visited[i]){
                visited[i] = true; //(= 같은 수를 출력하지 않기 위함 ex. 1,1)
                arr[depth] = i+1;
                dfs(n,m, depth+1);

                visited[i] = false;
            }
        }
    }


}