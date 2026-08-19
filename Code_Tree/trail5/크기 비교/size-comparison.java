import java.io.*; 
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] com = new int[N+1][N+1];

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int fr = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            com[fr][to] = 1;
        }

        StringBuilder sb = new StringBuilder();

        for(int k = 1; k <= N; k++){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    if(com[i][k] ==1 && com[k][j] == 1){
                        com[i][j] = 1;
                    }
                }
            }
        }

        for(int i = 1; i <= N; i++){
            int cnt = 0; 
            for(int j = 1; j <= N ; j++){
                if(i == j) continue;
                if(com[i][j] == 1 || com[j][i] == 1) continue;

                cnt++;
            }

            sb.append(cnt).append("\n");
        }

        System.out.print(sb);
    }
}