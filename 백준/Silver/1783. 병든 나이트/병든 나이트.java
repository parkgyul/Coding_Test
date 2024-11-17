import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

public class Main{
   static int n, m;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        System.out.println(solve());
    }
    public static int solve(){
        if(n==1) return 1;
        if(n==2) return Math.min(4, (m+1)/2);
        else{
            if(m<7){
                return Math.min(m,4);
            }
            else return m-2;
        }
    }
}