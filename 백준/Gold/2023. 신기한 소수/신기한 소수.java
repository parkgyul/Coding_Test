import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Main{
    static int n;
    static int[] arr;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        dfs(0, 0);
    }
    public static void dfs(long sum, int depth)
    {
        if(depth == n) {
            System.out.println(sum);
            return;
        }

        for(int j = 1; j<= 9; j++){
            long op = (long)(sum *10 + j);
            if(cal(op)) {
                dfs(op, depth+1);
            }
        }
    }
    public static boolean cal(long op){
        if(op == 1) return false;
        for(int i = 2; i<=(int)Math.sqrt(op); i++){
            if(op%i == 0) return false;
        }
        return true;
    }
}