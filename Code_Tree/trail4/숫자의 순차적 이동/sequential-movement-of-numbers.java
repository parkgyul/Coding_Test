import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][N];

         Map<Integer, Point> map = new HashMap<>();

        for(int i =0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                map.put(arr[i][j], new Point(i, j));
            }
        }

        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        while(M-- > 0){
            for(int i =1; i <= N*N; i++){
                Point p = map.get(i);
                int max = -1;
                for(int dir = 0; dir < 8; dir++){
                    int ni = p.i + dx[dir];
                    int nj = p.j + dy[dir];

                    if(ni < 0 || ni >= N || nj < 0 || nj >= N) continue;

                    max = Math.max(max, arr[ni][nj]);
                }

                Point newLoc = map.get(max);

                arr[p.i][p.j] = max;
                arr[newLoc.i][newLoc.j] = i;
                map.put(i, newLoc);
                map.put(max, p);
            }
        }

        for(int i =0 ; i < N; i++){
            for(int j = 0; j < N; j++){
                System.out.print(arr[i][j] + " ");
            }

            System.out.println();
        }
    }

    static class Point{
        int i, j;
        Point(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
}