import java.io.IOException;
import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class Main{
    static int N, M;
    static int[][] arr;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int max;
    static boolean[][] visited;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j <M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j <M; j++){
                visited[i][j] = true;
                dfs(i,j, 1, arr[i][j]);
                checkT(i,j);
                visited[i][j] = false;
            }
        }

        System.out.print(max);
    }

    public static void dfs(int i, int j, int depth, int total){

        if(depth == 4){
            max = Math.max(total, max);
            return;
        }

        for(int a = 0; a < 4; a++){
            int next_i = i + dx[a];
            int next_j = j + dy[a];

            if(next_i < 0 || next_j < 0 || next_i >= N || next_j >= M || visited[next_i][next_j])
                continue;

            visited[next_i][next_j] = true;
            dfs(next_i, next_j, depth+1, total + arr[next_i][next_j]);
            visited[next_i][next_j] = false;
        }
    }

    public static void checkT(int i, int j){
        int min = Integer.MAX_VALUE;
        int sum = arr[i][j];
        int cnt = 1;
        for(int a = 0; a< 4; a++){
            int next_i = i + dx[a];
            int next_j = j + dy[a];

            if(next_i < 0 || next_j < 0 || next_i >= N || next_j >= M )
                continue;

            min = Math.min(min, arr[next_i][next_j]);
            sum += arr[next_i][next_j];
            cnt ++;
        }

        if(cnt == 4){
            max = Math.max(max, sum);
        }else{
            max = Math.max(max, sum - min);
        }
    }
}
