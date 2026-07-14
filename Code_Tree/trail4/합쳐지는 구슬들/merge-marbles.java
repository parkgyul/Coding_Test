import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[] dx = {0, -1, 1, 0, 0};
        int[] dy = {0, 0, 0, -1, 1};

        int[][] arr = new int[N][N];
        int[][] map = new int[N][N];
        int[][] weight = new int[N][N];

        int[] bx = new int[M+1];
        int[] by = new int[M+1];
        int[] bw = new int[M+1];
        int[] bd = new int[M+1];

        for(int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            char chD = st.nextToken().charAt(0);
            int w = Integer.parseInt(st.nextToken());

            int d = 0;
            switch(chD){
                case 'L' : d = 3; break;
                case 'U' : d = 1; break;
                case 'R' : d = 4; break;
                case 'D' : d = 2; break;
            } 

            bx[i] = r;
            by[i] = c;
            bw[i] = w;
            bd[i] = d;

            arr[r][c] = i;
        }


        while(T-- > 0){

            map = new int[N][N];
            weight = new int[N][N];

            for(int i = 0; i < N; i++){
                for(int j =0 ; j < N; j++){
                    if(arr[i][j]>0){
                        int num = arr[i][j];
                        int d = bd[num];

                        int ni = bx[num] + dx[d];
                        int nj = by[num] + dy[d];

                        if(ni < 0 || nj < 0 || ni >= N || nj >= N){
                            ni = i;
                            nj = j;
                            d = (d % 2 == 0) ? d-1 : d+1;
                        }

                        bx[num] = ni;
                        by[num] = nj;
                        bd[num] = d;

                        weight[ni][nj] += bw[num];
                        map[ni][nj] = Math.max(map[ni][nj], num);
                    }
                }
            }

            for(int i =0 ; i < N; i++){
                for(int j =0 ; j < N; j++){
                    if(map[i][j] > 0){
                        bw[map[i][j]] = weight[i][j];
                    }
                }
            }

            arr = map;
        }

        int cnt = 0;
        int max = 0;
        for(int i =0 ; i < N; i++){
            for(int j =0 ; j < N; j++){
                if(arr[i][j] > 0){
                    cnt++;
                    max = Math.max(max, bw[arr[i][j]]);
                }
            }
        }

        System.out.println(cnt + " " + max);
    }
}