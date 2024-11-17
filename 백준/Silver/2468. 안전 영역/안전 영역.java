import java.awt.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Queue;
public class Main{
    static int size;
    static int[][] map;
    static int max;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        size = Integer.parseInt(br.readLine());
        max = 0;
        map = new int[size][size];

        for(int i = 0; i<size; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<size; j++){

                map[i][j]= Integer.parseInt(st.nextToken());
                if(max < map[i][j]) max = map[i][j];
            }
        }

        search();

    }
    public static void search(){
        Queue<Point> q = new LinkedList<>();
        int cnt =0, cnt_max = 1;

        for(int k =1; k<max; k++) {
            cnt = 0;

            visited = new boolean[size][size];

            for(int p =0; p<size; p++){
                for(int j = 0; j<size; j++){
                    if(!visited[p][j] && map[p][j] > k) {
                       cnt += dfs(p,j,k);
                    }
                }
            }
            if(cnt_max <cnt) cnt_max  = cnt;


        }
        System.out.println(cnt_max);
    }

    public static int dfs(int i, int j, int height){
        visited[i][j] = true;
        for(int k = 0; k<4 ; k++){
            int next_x = i + dx[k];
            int next_y = j + dy[k];
            if(next_x< 0 || next_y < 0 || next_x >= size || next_y>= size)
                continue;
            if(visited[next_x][next_y] || map[next_x][next_y] <= height)
                continue;
            dfs(next_x, next_y, height);

        }
        return 1;
    }


}