 import java.io.IOException;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

import java.util.Queue;
import java.util.LinkedList;

public class Main{
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static int R, C, max;
    static char[][] arr;
    static boolean[] selected;
    static Queue<Point> q;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[R][C];
//        visited = new boolean[R][C];
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
            if(selected[arr[next_i][next_j]-'A'])
                continue;

            selected[arr[next_i][next_j]-'A'] = true;
            dfs(next_i, next_j, cnt+1);
            selected[arr[next_i][next_j]-'A'] = false;
        }
    }

    public static class Point{
        int i, j, cnt;
        boolean[] selected;
        public Point(int i, int j, int cnt, boolean[] selected){
            this.i = i;
            this.j = j;
            this.cnt = cnt;
            this.selected = selected;
        }
    }
}