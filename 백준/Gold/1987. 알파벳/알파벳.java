import java.io.IOException;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class Main{
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static int R, C, max;
    static char[][] arr;
    static boolean[] selected;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[R][C];
        max = 1;

        for(int i = 0; i < R; i ++){
            String str = br.readLine();
            for(int j = 0; j < C; j++){
                arr[i][j] = str.charAt(j);
            }
        }
        selected = new boolean[26];
        selected[arr[0][0]-'A'] = true;

        dfs(0, 0,1);

        System.out.print(max);
    }
    public static void dfs(int i, int j, int cnt){

        if(cnt > max){
            max = cnt;
        }

        for(int a = 0; a <4; a++){
            int next_i = i + dx[a];
            int next_j = j + dy[a];

            if(next_i <0 || next_j <0 || next_i >= R || next_j >= C)
                continue;

            int alphabet = arr[next_i][next_j] - 'A';

            if(selected[alphabet])
                continue;

            selected[alphabet] = true;
            dfs(next_i, next_j, cnt+1);
            selected[alphabet] = false;
        }
    }
}