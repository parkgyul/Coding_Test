import java.util.*;
import java.io.*;

public class Main {
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        int ice = 0;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) ice++;
            }
        }
        
        boolean[][] visited = new boolean[n][m];
        Queue<Point> waters = new ArrayDeque<>();
        
        waters.add(new Point(0, 0));
        visited[0][0] = true;

        int time = 0;
        int lastMelted = 0;

        while(ice > 0) {
            int meltedCount = 0;
            Queue<Point> melted = new ArrayDeque<>();

            while(!waters.isEmpty()) {
                Point cur = waters.poll();

                for (int i = 0; i < 4; i++) {
                    int ny = cur.i + dy[i];
                    int nx = cur.j + dx[i];

                    if (ny < 0 || ny >= n || nx < 0 || nx >= m) continue;
                    if (visited[ny][nx]) continue;
                    
                    visited[ny][nx] = true;
                    
                    if (map[ny][nx] == 1) {
                        meltedCount++;
                        map[ny][nx] = 0;
                        melted.add(new Point(ny, nx));
                        ice--;
                    } else {
                        waters.add(new Point(ny, nx));
                    }
                }
            }
            
            lastMelted = meltedCount;
            time++;

            waters = melted;
        }
        System.out.println(time + " " + lastMelted);
    }

    static class Point{
        int i, j;
        Point(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
}
