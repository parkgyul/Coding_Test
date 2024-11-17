import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int n;
    static int[][] arr;
    static boolean[] visited;
    static int min = Integer.MAX_VALUE;
    static int[] start;

    public static void main(String[] args)throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n  = Integer.parseInt(br.readLine());
        StringTokenizer st ;

        arr = new int [n+1][n+1];
        for(int i =1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j= 1 ; j<=n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new boolean[n+1];
        visited[1] = true;
        start = new int[n/2 + 1];

        start[1] = 1;
        dfs(1);
        System.out.println(min);
    }
    static void dfs(int depth){
        if(depth == n/2){
            int start_sum = 0;
            int link_sum = 0;
//            for(int i = 1; i<= depth; i++){
//                System.out.printf(start[i]+ " ");
//            }
//            System.out.println();
//            System.out.println();

//            for(int i = 1; i <= depth-1; i++ ){
//                for(int j = i+1; j<=depth; j++){
//                    int a = start[i];
//                    int b = start[j];
//                    System.out.printf("/++"+a+" "+b+ " ++/");
//                    start_sum += (arr[a][b]+ arr[b][a]);
//                    link_sum += (arr[n+1-a][n+1-b]+ arr[n+1-b][n+1-a]);
//                }
//            }
            for(int i =1; i<=n-1; i++){
                for(int j=i; j<= n; j++){
                    if (visited[i] && visited[j]) {
                        start_sum += (arr[i][j]+ arr[j][i]);
                    }
                    if (!visited[i] && !visited[j]) {
                        link_sum += (arr[i][j]+ arr[j][i]);
                    }
                }
            }
            min = Math.min(min, Math.abs(start_sum-link_sum));
            return;
        }
        for(int i = 2; i<= n; i++){
            if(!visited[i] && i > start[depth]){
                visited[i] = true;
                start[depth+1] = i;
                dfs(depth+1);
                visited[i] = false;
            }
        }
    }
}