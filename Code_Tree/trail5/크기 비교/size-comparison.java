import java.io.*; 
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        final int INF = 10000000;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] com = new int[N+1][N+1];
        int[][] com2 = new int[N+1][N+1];

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int fr = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            com[fr][to] = 1;
            com2[to][fr] = 1;
        }

        StringBuilder sb = new StringBuilder();

        for(int k = 1; k <= N; k++){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    if(com[i][k] ==1 && com[k][j] == 1){
                        com[i][j] = 1;
                    }

                    if(com2[i][k] == 1 && com2[k][j] == 1){
                        com2[i][j]= 1; 
                    }
                }
            }
        }

        for(int i = 1; i <= N; i++){
            int cnt = 0; 
            for(int j = 1; j <= N ; j++){
                cnt += (com[i][j] + com2[i][j]);
            }

            sb.append(N-1-cnt).append("\n");
        }

        System.out.print(sb);
    }
}