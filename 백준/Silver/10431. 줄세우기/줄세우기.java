import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int[] arr;
        for(int i = 0; i<n; i++){
            arr = new int[20];
            st =  new StringTokenizer(br.readLine());
            sb.append(Integer.parseInt(st.nextToken())).append(" ");

            int total = 0;

            for(int j = 0; j < 20; j++){
                arr[j] = Integer.parseInt(st.nextToken());
                for(int k = j; k >= 0; k--){
                    if(arr[j] < arr[k])
                        total++;
                }
            }

            sb.append(total).append("\n");
        }

        System.out.print(sb);

    }
}