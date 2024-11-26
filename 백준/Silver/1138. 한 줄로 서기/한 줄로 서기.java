import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

class Main{

    static int[] arr ;
    static int n;
    static boolean[] visited;
    static int[] line;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        visited = new boolean[n+1];
        line = new int[n];

        a(0);
    }

    public static void a(int depth) {

        if(depth == n){
            for(int i = 0 ; i < n ; i++){
                int cnt = 0;

                for(int j = 0; j < i ; j++){
                    if( line[j] > line[i]){
                        cnt++;
                    }
                }
                if(cnt != arr[line[i]-1]) {
                    return;
                }
            }
            
                for(int i = 0; i<n; i++){
                    System.out.printf("%d ", line[i]);
                }
                
        }

        for(int i = 1; i <= n; i++){
            if(!visited[i]){
                visited[i] = true;
                line[depth] = i;
                a(depth+1);
                visited[i] = false;
            }
        }
    }
}