import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main{
    static int size;
    static int[][] map;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0, 1,-1};

    static boolean[][] visited;
    static int cnt = 0;
    static int total = 0;
    static ArrayList<Integer> arr;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        size = Integer.parseInt(br.readLine());

        map = new int[size][size];
        visited = new boolean[size][size];

        arr = new ArrayList<>();

        for(int i = 0; i<size; i++){
            String str = br.readLine();
            for(int j =0; j<size; j++){
                map[i][j] = str.charAt(j)-'0';
            }
        }

        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                if(!visited[i][j] && map[i][j] == 1){
                    cnt = 0;
                    total ++;
                    arr.add(dfs(i,j));
                }
            }
        }

        Collections.sort(arr);

        System.out.println(total);
        for(int i = 0; i< arr.size(); i++){
            System.out.println(arr.get(i));
        }

    }
    public static int dfs(int i, int j){
        cnt ++;
        visited[i][j] = true;
        for(int k =0; k<4; k++){
            int next_x = i + dx[k];
            int next_y = j + dy[k];
            if(next_x<0 || next_y<0 || next_y>= size || next_x >= size)
                continue;
            if(visited[next_x][next_y] || map[next_x][next_y] == 0)
                continue;
            dfs(next_x, next_y);
        }
        return cnt;
    }
}