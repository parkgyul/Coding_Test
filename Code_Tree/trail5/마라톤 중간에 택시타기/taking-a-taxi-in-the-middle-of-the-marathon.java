import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        int[] xArr = new int[N];
        int[] yArr = new int[N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            xArr[i] = x;
            yArr[i] = y;
        }

        int[] L = new int[N];
        int[] R = new int[N];

        for(int i = 1; i < N; i++){
            L[i] = L[i-1] + Math.abs(xArr[i] - xArr[i-1]) + Math.abs(yArr[i] - yArr[i-1]);
        }

        for(int i = N-2; i >= 0; i--){
            R[i] = R[i+1] + Math.abs(xArr[i] - xArr[i+1]) + Math.abs(yArr[i] - yArr[i+1]);
        }

        int min = Integer.MAX_VALUE;

        for(int i = 1; i <= N-2; i++){
            min = Math.min(min, (L[i-1] + Math.abs(xArr[i+1] - xArr[i-1]) + Math.abs(yArr[i+1] - yArr[i-1]) + R[i+1]));
        }

        System.out.print(min);
    }
}