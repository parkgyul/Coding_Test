import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
import java.io.BufferedReader;

public class Main{
    static int N, M, H;
    static boolean[][] visited;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 세로선 개수
        M = Integer.parseInt(st.nextToken()); // 사다리 개수
        H = Integer.parseInt(st.nextToken()); // 가로선 개수

        visited = new boolean[H+1][N+1];

        for(int i =0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            visited[r][c] = true;
        }

        dfs(0, 0, 0);

        System.out.print(min == Integer.MAX_VALUE ? -1 : min);
    }

    public static void dfs(int depth, int i, int j){
        if(depth > 3 || depth >= min) return;

        if(isPossible()){
            min = depth;
            return;
        }

        for(int a = i; a < H+1; a++){
            for(int b = (a == i ? j : 1); b < N; b++){
                if(visited[a][b]) continue;
                if(b >= 2 && visited[a][b-1]) continue;
                if(b < N-1 && visited[a][b+1]) continue;

                visited[a][b] = true;
                dfs(depth+1, a, b);
                visited[a][b] = false;
            }
        }
    }

    public static boolean isPossible(){
        for(int start = 1; start <= N; start++){
            int idx = start;
            for(int h = 1; h <= H; h++){
                if(visited[h][idx]) idx++;
                else if(idx > 1 && visited[h][idx-1]) idx--;
            }
            if(idx != start) return false;
        }
        return true;
    }
}