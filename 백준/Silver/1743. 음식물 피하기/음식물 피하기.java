import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Queue;

public class Main{

    static int h,w,garbage;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};


    public static void main(String[] args)throws IOException{
        int max =1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        garbage = Integer.parseInt(st.nextToken());

        map = new int[h+1][w+1];
        visited = new boolean[h+1][w+1];

        for(int i = 0; i<garbage; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[r][c] = 1;
        }

        
        for(int i = 1; i <= h; i++){
            for(int j = 1; j<= w; j++){
                if(map[i][j] == 1 && !visited[i][j]){
                    int result = search(i,j);
                    if(result> max) max = result;
                }
            }
        }
        System.out.print(max);
    }

    public static int search(int i, int j){
        int sum = 1;
        int next_x, next_y;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(i,j));
        visited[i][j] = true;
        while(!q.isEmpty()){
            Point start = q.poll();
            for(int k = 0; k<4; k++){
                next_x = start.x + dx[k];
                next_y = start.y + dy[k];
                if(next_x <1 || next_x > h || next_y <1 || next_y > w)
                    continue;
                if(visited[next_x][next_y] || map[next_x][next_y] != 1)
                    continue;
                q.add(new Point(next_x, next_y));
                visited[next_x][next_y]= true;
                sum++;
            }
        }
        return sum;
    }

}