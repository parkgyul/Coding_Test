import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class Main{
    static int R, C;
    static int[] dx = {-1, 0, 1};// 대각선 위, 오른쪽, 대각선 아래
    static int[] dy = {1, 1, 1};
    static char[][] arr;
    static boolean[][] visited;
    static boolean flag = false;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[R][C];

        for(int i = 0; i < R; i++){
            String str = br.readLine();
            for(int j = 0; j < C; j++){
                arr[i][j] = str.charAt(j);
            }
        }

        int cnt = 0;
        visited = new boolean[R][C];

        for(int i = 0; i < R; i++){
            dfs(i, 0);
            if(flag) cnt++;
            flag = false;
        }

        System.out.print(cnt);
    }

    public static void dfs(int i, int j){
        if(j == C-1){
            flag = true;
            return;
        }

        for(int a = 0; a < 3; a++){
            int ni = i + dx[a];
            int nj = j + dy[a];

            if(ni < 0 || ni >= R || nj < 0 || nj >= C)
                continue;

            if(visited[ni][nj] || arr[ni][nj] == 'x') continue;

            visited[ni][nj] = true;
            dfs(ni, nj);
            
            if(flag) return;
        }
    }

    public static class Point {
        int i, j;

        public Point( int i, int j){
            this.i = i;
            this.j = j;
        }
    }
}