import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

public class Main{
    static int[][] map;
    static int[][] copy;
    static int max = Integer.MIN_VALUE;
    static int N, M;
    static List<Point> emptyList = new ArrayList<>();
    static List<Point> virusList = new ArrayList<>();
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) emptyList.add(new Point(i, j));
                else if(map[i][j] == 2) virusList.add(new Point(i, j));
            }
        }

        copy = new int[N][M];

        for(int i = 0; i < emptyList.size()-2; i++){
            Point cur = emptyList.get(i);
            map[cur.i][cur.j] = 1;
            dfs(1, i);
            map[cur.i][cur.j] = 0;
        }

        System.out.print(max);
    }
    public static void dfs(int depth, int start){
        if(depth == 3){
            bfs();
            return;
        }

        for(int i = start+1; i < emptyList.size(); i++){
            Point cur = emptyList.get(i);
            map[cur.i][cur.j] = 1;
            dfs(depth+1, i);
            map[cur.i][cur.j] = 0;
        }
    }

    public static void bfs(){
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for(int j = 0; j < N; j++){
            copy[j] = map[j].clone();
        }

        for (Point p : virusList) {
            q.add(new Point(p.i, p.j));
            visited[p.i][p.j] = true;
        }

        while(!q.isEmpty()){
            Point cur = q.poll();

            for(int i = 0; i < 4; i++) {
                int next_i = cur.i + dx[i];
                int next_j = cur.j + dy[i];

                if (next_i < 0 || next_j < 0 || next_i >= N || next_j >= M)
                    continue;

                if (visited[next_i][next_j])
                    continue;

                if(copy[next_i][next_j] == 0){
                    visited[next_i][next_j] = true;
                    q.add(new Point(next_i, next_j));
                    copy[next_i][next_j] = 2;
                }
            }
        }

        int cnt = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(copy[i][j] == 0)
                    cnt ++;
            }
        }
        max = Math.max(cnt, max);
    }

    public static class Point{
        int i, j;

        public Point(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
}