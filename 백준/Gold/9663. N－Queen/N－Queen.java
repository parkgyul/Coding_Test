import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    static int arr[];
    static int n;
    static int total = 0;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dfs(0);
        System.out.println(total);
    }
    public static void dfs(int depth){

        if(depth == n){
            total ++;
            return;
        }
        for(int i =0; i<n; i++){
            arr[depth] = i;
            if(check(depth)){
                dfs(depth+1);
            }
        }
    }
    public static boolean check(int col){

        for(int i = 0; i<col; i++){
            if(arr[i] == arr[col])
                return false;
            if(Math.abs(i-col) == Math.abs(arr[i]-arr[col]))
                return false;
        }

        return true;
    }

}