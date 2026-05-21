import java.io.*; 
import java.util.*; 

public class Main {
    static int N;
    static int ei, ej;
    static boolean[][] visited;

    static int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int si = Integer.parseInt(st.nextToken())-1;
        int sj = Integer.parseInt(st.nextToken())-1;
        ei = Integer.parseInt(st.nextToken())-1;
        ej = Integer.parseInt(st.nextToken())-1;

        if(si == ei && sj == ej){
            System.out.print(0);
            return;
        }

        visited = new boolean[N][N];

        System.out.print(bfs(si, sj));
    }

    static int bfs(int si, int sj){
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(si, sj, 0));
        visited[si][sj] = true;

        while(!q.isEmpty()){
            Point cur = q.poll();

            for(int i = 0; i < 8; i++){
                int ni = cur.i + dx[i];
                int nj = cur.j + dy[i];

                if(ni == ei && nj == ej){
                    return cur.cnt+1;
                }

                if(ni < 0 || nj < 0 || ni >= N || nj >= N) continue;
                if(visited[ni][nj]) continue;

                visited[ni][nj] = true;
                q.add(new Point(ni, nj, cur.cnt+1));
            }
        }

        return -1;
    }

    static class Point{
        int i, j, cnt;

        Point(int i, int j, int cnt){
            this.i = i;
            this.j = j;
            this.cnt = cnt;
        }
    }
}