import java.io.*;
import java.util.*;
public class Main {
    static int N;
    static int[][] arr;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        StringTokenizer st;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0 ; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken())-1;
        int c = Integer.parseInt(st.nextToken())-1;

        int num = burst(r, c);
        pull(num, r, c);

         for(int i = 0; i < N; i++){
            for(int j =0 ; j < N; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    static int burst(int r, int c){
        int num = arr[r][c];

        for(int i = 1; i <= num; i++){
            for(int dir = 0 ; dir < 4; dir++){
                int ni = r + dx[dir]*(i-1);
                int nj = c + dy[dir]*(i-1);

                if(ni < 0 || nj < 0 || nj >= N || ni >= N) continue;

                arr[ni][nj] = 0;
            }
        }

        return num;
    }

    static void pull(int num, int r, int c){
        //가로
        for(int j = 1; j <= num-1; j++){
            if(c-j >= 0){
                for(int i = r; i >= 1; i--){
                    arr[i][c-j] = arr[i-1][c-j];
                }
                arr[0][c-j] = 0;
            }
            if(c+j < N){
                for(int i = r; i >= 1; i--){
                    arr[i][c+j] = arr[i-1][c+j];
                }
                arr[0][c+j] = 0;
            }
        }

        int rDown = r+num-1 < N ? r+num-1 : N-1;
        int rUp = r-num > 0 ? r-num : 0;
        int gap = rDown - rUp;

        for(int i = 0; i <= rUp; i++){
            arr[rDown-i][c] = arr[rDown-i-gap][c];
            arr[rDown-i-gap][c] = 0;
        }
    }
}