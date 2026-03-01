import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

class Main{
    static int n, m, cnt;
    static Queue<Point> q;
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, -1, 1, 0};
    static int[][] arr;
    static boolean[][] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for(int i = 0; i < t; i++){ // 테스트 케이스
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken()); // 가로 길이
            n = Integer.parseInt(st.nextToken()); // 세로 길이
            int k = Integer.parseInt(st.nextToken()); // 배추 심어져있는 위치 개수

            arr = new int[n][m];
            // 1. 배추 위치 입력 받아서 저장
            for(int j = 0; j < k; j++){ // 배추 개수
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                // 배추를 심어요.
                arr[y][x] = 1;
            }

            q = new LinkedList<>();
            visited = new boolean[n][m];
            cnt = 0;

            // 2. 순회하면서 배추 영역 찾기
            for(int a = 0; a < n; a++){
                for(int b = 0; b < m; b++){
                    if(arr[a][b] == 1 && !visited[a][b]){ // 배추 발견!!
                        q.add(new Point(a,b));
                        bfs();
                    }
                }
            }
            sb.append(cnt).append("\n");
        }
        System.out.print(sb);
    }

    public static void bfs(){
        while(!q.isEmpty()){
            Point current = q.poll();

            for(int i = 0; i<4; i++){
                int next_i = current.i + dx[i];
                int next_j = current.j + dy[i];

                if(next_i <0 || next_j < 0 || next_i >= n || next_j >= m) {
                    continue;
                }

                if(arr[next_i][next_j] != 1 || visited[next_i][next_j]) {
                    continue;
                }

                q.add(new Point(next_i, next_j));
                visited[next_i][next_j] = true;

            }
        }
        cnt++;
    }
    public static class Point {
        int i, j;
        public Point(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
}