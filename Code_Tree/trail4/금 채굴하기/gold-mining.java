import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};

        int answer = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int sum = 0;

                if(arr[i][j] == 1){
                    sum++;
                    answer = Math.max(1, answer);
                }

                List<int[]> list = new ArrayList<>();
                list.add(new int[]{i, j});
                boolean[][] visited = new boolean[N][N];
                visited[i][j] = true;
                for(int k = 1; k <= N; k++){
                    List<int[]> newList = new ArrayList<>();

                    for(int[] rc : list){
                        for(int dir = 0; dir < 4; dir++){
                            int ni = rc[0] + dx[dir];
                            int nj = rc[1] + dy[dir];

                            if(ni < 0 || ni >= N || nj < 0 || nj >= N || visited[ni][nj]) continue;

                            visited[ni][nj] = true;
                            sum += arr[ni][nj];
                            newList.add(new int[]{ni, nj});
                        }
                    }

                    int cost = k * k + (k + 1) * (k + 1);

                    if (sum * M >= cost) {
                        answer = Math.max(answer, sum);
                    }

                    list = newList;
                }
            }
        }

        System.out.print(answer);
    }
}