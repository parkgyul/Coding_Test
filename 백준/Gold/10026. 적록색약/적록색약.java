import java.awt.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

import static java.lang.Math.abs;

class Main{
    static int n;
    static char[][] arr;
    static boolean[][] normal_visited ;
    static boolean[][] abnormal_visited;
    static int dx[] = {0, 0, 1, -1};
    static int dy[] = {1,-1, 0, 0};
    static int nor = 0;
    static int ab = 0;

    static Queue<Point> q1 = new LinkedList<>();
    static Queue<Point> q2 = new LinkedList<>();
    //RGB
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new char[n][n];

        normal_visited = new boolean[n][n];
        abnormal_visited = new boolean[n][n];

        for(int i = 0; i<n; i++){
            String str = br.readLine();
            for(int j = 0; j<n; j++){
                arr[i][j] = str.charAt(j);
            }
        }

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(!normal_visited[i][j])
                {
                    q1.add(new Point(i,j));
                    bfs_normal();
                }
                if(!abnormal_visited[i][j]){
                    q2.add(new Point(i,j));
                    bfs_abnormal();
                }
            }
        }
        System.out.println(nor+ " "+ ab);
    }
    public static void bfs_normal(){
        while(!q1.isEmpty()){
            Point current = q1.poll();
            for(int i = 0; i <4; i++){
                int next_i = current.x + dx[i];
                int next_j = current.y + dy[i];

                if(next_i < 0 || next_i >= n || next_j < 0 || next_j >= n)
                    continue;
                if(normal_visited[next_i][next_j])
                    continue;
                if(arr[next_i][next_j] != arr[current.x][current.y])
                    continue;

                q1.add(new Point(next_i, next_j));
                normal_visited[next_i][next_j] = true;
            }
        }
        nor ++;
    }
    public static void bfs_abnormal(){
        while(!q2.isEmpty()) {
            Point current = q2.poll();
            for (int i = 0; i < 4; i++) {
                int next_i = current.x + dx[i];
                int next_j = current.y + dy[i];

                if (next_i < 0 || next_i >= n || next_j < 0 || next_j >= n)
                    continue;
                if (abnormal_visited[next_i][next_j])
                    continue;
                if(arr[current.x][current.y] == 'B' && arr[next_i][next_j] != 'B')
                    continue;
                if(arr[current.x][current.y] == 'R' && arr[next_i][next_j] == 'B')
                    continue;
                if(arr[current.x][current.y] == 'G' && arr[next_i][next_j] == 'B')
                    continue;


                q2.add(new Point(next_i, next_j));
                abnormal_visited[next_i][next_j] = true;
            }
        }
        ab++;
    }

}
