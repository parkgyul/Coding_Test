import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][][] prefix = new int[N+1][M+1][3];
        for(int i = 1 ; i <= N; i++){
            String str = br.readLine();
            for(int j = 1; j <= M; j++){
                char ch = str.charAt(j-1);

                int c = -1;
                switch(ch){
                    case 'a' : c = 0; break;
                    case 'b' : c = 1; break;
                    case 'c' : c = 2; break;
                }

                for(int k = 0; k < 3; k++){
                    prefix[i][j][k] = prefix[i-1][j][k] + prefix[i][j-1][k] - prefix[i-1][j-1][k] + ((k == c) ? 1 : 0);
                }
                
            }
        }
        
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int si = Integer.parseInt(st.nextToken());
            int sj = Integer.parseInt(st.nextToken());
            int ei = Integer.parseInt(st.nextToken());
            int ej = Integer.parseInt(st.nextToken());

            for(int c = 0; c <3; c++){
                int value = (prefix[ei][ej][c] - prefix[si-1][ej][c] - prefix[ei][sj-1][c] + prefix[si-1][sj-1][c]);
                sb.append(value + " ");
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }
}