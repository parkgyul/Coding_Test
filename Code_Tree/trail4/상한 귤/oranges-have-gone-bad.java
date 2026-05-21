import java.io.*; 
import java.util.*; 

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        int[][] result = new int[N][N];
        Queue<Point> rotten = new LinkedList<>();

        for(int i =0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0 ; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0){ // 처음부터 비어 있던 칸
                    result[i][j] = -1;
                }else if(map[i][j] == 1){ // 귤
                    result[i][j] = -2;
                }else{// 상한 귤
                    result[i][j] = 0;
                    rotten.add(new Point(i, j));
                }
            }
        }

        Queue<Point> changed;
        int[] dx = {1, 0, 0, -1};
        int[] dy = {0, 1, -1, 0};
        boolean[][] visited;
        int time = 1;
        while(true){
            visited = new boolean[N][N];
            changed = new LinkedList<>();
            while(!rotten.isEmpty()){
                Point cur = rotten.poll();

                for(int i =0 ; i < 4; i++){
                    int ni = cur.i + dx[i];
                    int nj = cur.j + dy[i];

                    if(ni < 0 || ni >= N || nj < 0 || nj >= N) continue;
                    if(visited[ni][nj] || map[ni][nj] != 1) continue;

                    visited[ni][nj] = true;
                    result[ni][nj] = time;
                    map[ni][nj] = 2;
                    // System.out.println(time + " : " + ni + " " + nj);
                    changed.add(new Point(ni, nj));
                }
            }

            if(changed.isEmpty()){
                break;
            }

            rotten = changed;
            time++;
        }

        StringBuilder sb = new StringBuilder();

        for(int i =0; i < N; i++){
            for(int j =0 ; j < N; j++){
                sb.append(result[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }


    static class Point{
        int i, j;
        Point(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
}