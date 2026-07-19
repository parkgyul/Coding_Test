import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int[][] prefix = new int[N+1][4];

        for(int i = 1; i <= N; i++){
            int num = Integer.parseInt(br.readLine());
            for(int j = 1; j <= 3; j++){
                prefix[i][j] = prefix[i-1][j] + ((num == j) ? 1 : 0);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken());

            for(int j = 1; j <= 3; j++){
                int value = prefix[e][j] - prefix[s][j];
                sb.append(value + " ");
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }
}