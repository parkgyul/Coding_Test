import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        int[] arr = new int[200001];

        int max = 0;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            if(max < x2) max = x2;
            arr[x1] += 1;
            arr[x2] -= 1;
        }

        int sum = 0;
        int answer = 0;
        for(int i = 1; i <= max; i++){
            sum += arr[i];

            if(answer < sum) answer = sum;
        }

        System.out.print(answer);
    }
}