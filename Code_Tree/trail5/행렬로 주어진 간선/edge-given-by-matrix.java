import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        int N = Integer.parseInt(br.readLine());
        int[][] possible = new int[N+1][N+1];
        
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                possible[i][j] = Integer.parseInt(st.nextToken());

                if(i == j) possible[i][j] = 1;
            }
        }

        for(int k = 1; k <= N; k ++){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    if(possible[i][k] == 1 && possible[k][j] == 1) possible[i][j] = 1;
                }
            }
        }

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                System.out.print(possible[i][j] + " ");
            }

            System.out.println();
        }
    }
}