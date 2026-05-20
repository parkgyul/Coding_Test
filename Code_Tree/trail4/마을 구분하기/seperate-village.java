import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static List<Integer> list;
    static int cnt;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];
        list = new ArrayList<>();

        StringTokenizer st;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] == 1 && !visited[i][j]){
                    cnt = 1;
                    visited[i][j] = true;
                    dfs(i, j);
                    list.add(cnt);
                }
            }
        }

        Collections.sort(list); 
        System.out.println(list.size());
        for(int num : list){
            System.out.println(num);
        }

    }

    static void dfs(int prevI, int prevJ){
        for(int i =0 ; i < 4; i++){
            int ni = prevI + dx[i];
            int nj = prevJ + dy[i];

            if(ni < 0 || ni >= N || nj < 0 || nj >= N) continue;
            if(visited[ni][nj] || map[ni][nj] == 0) continue;

            visited[ni][nj] = true;
            cnt++;
            dfs(ni, nj);
        }
    }
}