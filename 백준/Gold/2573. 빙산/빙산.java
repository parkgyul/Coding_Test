import java.io.IOException;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class Main{
    static int n,m;
    static int[][] arr;
    static int[] di = {-1,1,0,0};
    static int[] dj = {0,0,1,-1};
    static boolean[][] visited;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        visited = new boolean[n][m];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j <m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        int count = 0;
        while (true) {
            count = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (!visited[i][j] && arr[i][j] > 0) {
                        dfs(i, j);
                        count++;
                    }
                }
            }
            
            if (count >= 2) {
                System.out.println(cnt); // 2개 이상 분리됨
                return;
            }

            if (count == 0) {
                System.out.println("0"); // 빙산이 모두 녹음
                return;
            }


            melt(); // 빙산 녹이기
            cnt++;

            // 방문 배열 초기화
            for (int i = 0; i < n; i++) {
                Arrays.fill(visited[i], false);
            }
        }


    }

    public static void dfs(int i,int j){

            for (int a = 0; a < 4; a++) {
                int next_i = i + di[a];
                int next_j = j + dj[a];

                if(arr[next_i][next_j] <= 0)
                    continue;
                if (next_i < 1 || next_i > n - 2 || next_j < 1 || next_j > m - 2)
                    continue;
                if (visited[next_i][next_j])
                    continue;


                visited[next_i][next_j] = true;
                dfs(next_i,next_j);
            }

    }

    public static void melt() {
        int[][] temp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] > 0) {
                    int water = 0;
                    for (int d = 0; d < 4; d++) {
                        int ni = i + di[d];
                        int nj = j + dj[d];
                        if (ni >= 0 && ni < n && nj >= 0 && nj < m && arr[ni][nj] == 0) {
                            water++;
                        }
                    }
                    temp[i][j] = Math.max(0, arr[i][j] - water);
                }
            }
        }

        arr = temp; // 한 턴 뒤 갱신
    }
}