import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][N];

        int[] x = new int[N*N+1];
        int[] y = new int[N*N+1];

        for(int i =0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                x[arr[i][j]] = i;
                y[arr[i][j]] = j;
            }
        }

        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        while(M-- > 0){
            for(int i =1; i <= N*N; i++){
                int ci = x[i];
                int cj = y[i];

                int max = -1;
                for(int dir = 0; dir < 8; dir++){
                    int ni = ci + dx[dir];
                    int nj = cj + dy[dir];

                    if(ni < 0 || ni >= N || nj < 0 || nj >= N) continue;

                    max = Math.max(max, arr[ni][nj]);
                }

                int maxI = x[max];
                int maxJ = y[max];

                x[max] = ci;
                y[max] = cj;
                x[i] = maxI;
                y[i] = maxJ;

                arr[maxI][maxJ] = i;
                arr[ci][cj] = max;
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