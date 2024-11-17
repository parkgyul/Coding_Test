import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.util.Queue;
public class Main{
    static int n;
    static int[] arr;
    static int[] op ;
    static int max= Integer.MIN_VALUE, min = Integer.MAX_VALUE ;


    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        op = new int[4];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }

        dfs(arr[0], 0);
        System.out.println(max);
        System.out.println(min);
    }
    static void dfs(int sum, int depth){

        if(depth == n-1){
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }

        for(int i = 0; i<4; i++){
            if(op[i] > 0){
                op[i]--;

                switch (i){
                    case(0):
                        dfs(sum+arr[depth+1], depth+1);
                        break;
                    case(1):
                        dfs(sum-arr[depth+1], depth+1);
                        break;
                    case(2):
                        dfs(sum*arr[depth+1], depth+1);
                        break;
                    case(3):
                        dfs(sum/arr[depth+1], depth+1);
                        break;
                }
                op[i]++;

            }
        }
    }

}