import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Main{
    static int m, n, square ;
    static boolean[][] arr;

    static ArrayList<Integer> result;
    static int cnt = 0 ;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        square = Integer.parseInt(st.nextToken());

        arr = new boolean[m][n];
        result = new ArrayList<Integer>();

        for(int i = 0; i< square; i++){

            st = new StringTokenizer(br.readLine());
            int left_i = Integer.parseInt(st.nextToken());
            int left_j = Integer.parseInt(st.nextToken());
            left_j = m-1-left_j;
            arr[left_j][left_i] = true;

            int right_i = Integer.parseInt(st.nextToken());
            int right_j = Integer.parseInt(st.nextToken());
            right_i -= 1;
            right_j = m- right_j;
            arr[right_j][right_i] = true;

            for(int j = left_j; j>= right_j; j --){
                for(int p = left_i; p <= right_i; p++){
                    arr[j][p] = true;
                }
            }
        }

        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(!arr[i][j]){
                    cnt= 1;
                    arr[i][j] = true;
                    dfs(i,j);
                    result.add(cnt);
                }
            }
        }

        Collections.sort(result);

        System.out.println(result.size());
        for(int i = 0; i < result.size(); i++){
            System.out.printf("%d ", result.get(i));
        }
    }

    public static void dfs(int i, int j){
        int[] di = {1,-1,0,0};
        int[] dj = {0,0,1,-1};

        for(int dir = 0; dir < 4; dir++){
            int next_i = i + di[dir];
            int next_j = j + dj[dir];

            if(next_i < 0 || next_i >= m || next_j <0 || next_j >= n)
                continue;
            if(arr[next_i][next_j])
                continue;

            arr[next_i][next_j] = true;
            cnt ++;
            dfs(next_i,next_j);
        }
    }
}