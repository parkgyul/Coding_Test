import java.io.*;
import java.util.*;

public class Main {
    static final int TOP = 0, BOTTOM = 1, NORTH = 2, SOUTH = 3, EAST = 4, WEST = 5;
    static int[] dice = new int[6];
    static int[][] grid;
    static int N, M;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()) -1;
        int startR = Integer.parseInt(st.nextToken()) -1;
        int startC = Integer.parseInt(st.nextToken()) -1;

        dice[TOP] = 1;
        dice[BOTTOM] = 6;
        dice[WEST] = 4;
        dice[EAST] = 3;
        dice[SOUTH] = 2;
        dice[NORTH] = 5;

        String[] commands = br.readLine().trim().split(" ");

        grid = new int[N][N];
        int r = startR, c = startC;

        grid[r][c] = dice[BOTTOM];

        for(String cmd : commands){
            int nr = r, nc = c;
            switch(cmd){
                case "L" : if(c-1 >= 0) { nc = c - 1; rollWest(); } break;
                case "R" : if(c+1 < N) { nc = c + 1; rollEast(); } break;
                case "U" : if(r-1 >= 0) { nr = r - 1; rollNorth(); } break;
                case "D" : if(r+1 < N) { nr = r + 1; rollSouth();} break;
            }

            r = nr; c = nc;
            grid[r][c] = dice[BOTTOM];
        }

        int sum = 0;
        for(int i = 0 ; i < N; i++){
            for(int j =0 ; j < N; j++){
                sum += grid[i][j];
            }
        }

        System.out.print(sum);
    }

    static void rollEast(){
        int t = dice[TOP];
        dice[TOP] = dice[WEST];
        dice[WEST] = dice[BOTTOM];
        dice[BOTTOM] = dice[EAST];
        dice[EAST] = t;
    }
    static void rollWest(){
        int t = dice[TOP];
        dice[TOP] = dice[EAST];
        dice[EAST] = dice[BOTTOM];
        dice[BOTTOM] = dice[WEST];
        dice[WEST] = t;
    }

    static void rollNorth(){
        int t = dice[TOP];
        dice[TOP] = dice[SOUTH];
        dice[SOUTH] = dice[BOTTOM];
        dice[BOTTOM] = dice[NORTH];
        dice[NORTH] = t;
    }

    static void rollSouth(){
        int t = dice[TOP];
        dice[TOP] = dice[NORTH];
        dice[NORTH] = dice[BOTTOM];
        dice[BOTTOM] = dice[SOUTH];
        dice[SOUTH] = t;
    }
}