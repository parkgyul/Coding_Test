import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];

        for(int i = 0; i < n; i++){
             String str = br.readLine();
            for(int j = 0; j<m; j++){
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        int max = 1;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                int num = arr[i][j];
                for(int k = 0; k<50; k++){
                    if(i+k >= n || j+k >= m) break;
                    if(num != arr[i+k][j]) continue;
                    if(num != arr[i+k][j+k]) continue;
                    if(num != arr[i][j+k]) continue;

                    max = (int) Math.max(Math.pow(k+1, 2), max);
                }
            }
        }

        System.out.print(max);
    }
}