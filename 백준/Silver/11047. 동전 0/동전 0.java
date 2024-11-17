import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class Main {
    static int n, k ;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =  new StringTokenizer(br.readLine());;

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n];

        for(int i = 0; i <n ; i++)
        {
            arr[i] = Integer.parseInt(br.readLine());
        }
       coin();
    }
    public static void coin(){
        int sum = 0;
        for(int i = n-1; i >= 0; i--){
            if(k/arr[i] >= 1){
                sum += k/arr[i];
                k = k%arr[i];
            }
        }
        System.out.println(sum);
    }
}