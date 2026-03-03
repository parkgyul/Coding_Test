import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[(int)Math.pow(n,2)];

        StringTokenizer st;
        for(int i = 0; i< n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                arr[i*n+j] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(arr);

        System.out.print(arr[(int)Math.pow(n,2)-n]);
    }
}