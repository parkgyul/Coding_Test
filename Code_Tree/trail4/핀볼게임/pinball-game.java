import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-1, 0, 1, 0}; 
    static int[] dy = {0, 1, 0, -1};
    static int[][] arr;
    static int N;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];
        StringTokenizer st;
        
        for(int i =0 ; i < N; i ++){
            st = new StringTokenizer(br.readLine());

            for(int j =0 ; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = Integer.MIN_VALUE;

        for(int m = 0; m < N; m++){
            max = Math.max(max, findPath(2, 0, m));
            max = Math.max(max, findPath(0, N-1, m));
            max = Math.max(max, findPath(1, m, 0));
            max = Math.max(max, findPath(3, m, N-1));
        }

        System.out.print(max);
    }

    static int findPath(int dir, int i, int j){
        int si = i;
        int sj = j;
        int sdir = dir;
        int cnt = 1;

        while(true){
            if(arr[si][sj] == 1){
                sdir ^= 1;
            }else if(arr[si][sj] == 2){
                sdir = 3-sdir;
            }

            int ni = si + dx[sdir];
            int nj = sj + dy[sdir];

            if(ni < 0 || nj >= N || nj < 0 || ni >= N){
                cnt++;
                break;
            }

            si = ni; sj = nj;
            cnt++;
        }

        return cnt;
    }
}