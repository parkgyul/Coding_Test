import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] count = new int[100001];
        int[] arr = new int[N+1];
        for(int i = 1; i <= N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int j = 0;
        int max = 0;

        for(int i = 1; i <= N; i++){
            while(j+1 <= N && count[arr[j+1]] < 1){
                count[arr[j+1]]++;
                j++;
            }

            max = Math.max(max, j-i+1);

            count[arr[i]] --;
        }

        System.out.print(max);
    }
}