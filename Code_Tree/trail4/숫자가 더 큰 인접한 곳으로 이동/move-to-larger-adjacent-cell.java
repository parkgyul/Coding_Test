import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken())-1;
        int c = Integer.parseInt(st.nextToken())-1;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int[][] arr = new int[N][N];
        boolean[][] visited = new boolean[N][N];

        List<Integer> list = new ArrayList<>();

        for(int i =0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0 ; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true){
            if(visited[r][c]) break;
            visited[r][c] = true;
            list.add(arr[r][c]);

            int num = arr[r][c];

            for(int dir = 0; dir < 4; dir++){
                int ni = r + dx[dir];
                int nj = c + dy[dir];

                if(ni < 0 || ni >= N || nj < 0 || nj >= N) continue;
                if(visited[ni][nj]) continue;
                if(num >= arr[ni][nj]) continue;

                r = ni;
                c = nj;
                break;
            }
        }

        for(int num : list){
            System.out.print(num + " ");
        }

    }
}