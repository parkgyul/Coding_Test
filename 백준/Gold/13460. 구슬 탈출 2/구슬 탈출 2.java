import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    static int N, M;
    static char[][] board;
    static boolean[][][][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        visited = new boolean[N][M][N][M];

        int startRi = 0, startRj = 0, startBi = 0, startBj = 0;
        for(int i =0 ; i < N; i++){
            String str = br.readLine();
            for(int j = 0; j < M; j++){
                board[i][j] = str.charAt(j);
                if(board[i][j] == 'R'){
                    startRi = i;
                    startRj = j;
                    board[i][j] = '.';
                }else if(board[i][j] == 'B'){
                    startBi = i;
                    startBj = j;
                    board[i][j] = '.';
                }
            }
        }

        System.out.print(bfs(startRi, startRj, startBi, startBj));
    }

    public static int bfs(int startRi, int startRj, int startBi, int startBj){
        Queue<State> q = new LinkedList<>();
        q.add(new State(startRi, startRj, startBi, startBj, 0));
        visited[startRi][startRj][startBi][startBj] = true;
        while(!q.isEmpty()){
            State cur = q.poll();

            if(cur.time >= 10)
                continue;

            for(int i = 0; i < 4; i++) {
                moveResult red = move(cur.ri, cur.rj, i);
                moveResult blue = move(cur.bi, cur.bj, i);

                if (blue.inHole)
                    continue;

                if (red.inHole)
                    return cur.time + 1;

                int nri = red.i;
                int nrj = red.j;
                int nbi = blue.i;
                int nbj = blue.j;

                if (nri == nbi && nrj == nbj) {
                    if (red.count > blue.count) {
                        nri -= dx[i];
                        nrj -= dy[i];
                    } else {
                        nbi -= dx[i];
                        nbj -= dy[i];
                    }
                }

                if (!visited[nri][nrj][nbi][nbj]) {
                    visited[nri][nrj][nbi][nbj] = true;
                    q.add(new State(nri, nrj, nbi, nbj, cur.time+1));
                }
            }

        }

        return -1;
    }

    public static moveResult move(int i, int j, int dir){
        int count= 0;
        int ni = i;
        int nj = j;
        while(true){
            int ti = ni + dx[dir];
            int tj = nj + dy[dir];

            if(board[ti][tj] == '#') break;

            ni = ti;
            nj = tj;
            count++;

            if(board[ni][nj] == 'O') {
                return new moveResult(ni, nj, count, true);
            }
        }

        return new moveResult(ni, nj, count, false);
    }

    public static class moveResult{
        int i, j, count;
        boolean inHole;

        public moveResult(int i, int j, int count, boolean inHole){
            this.i = i;
            this.j = j;
            this.count = count;
            this.inHole = inHole;
        }
    }
    public static class State{
        int ri, rj, bi, bj, time;

        public State(int ri, int rj, int bi, int bj, int time){
            this.ri = ri;
            this.rj = rj;
            this.bi = bi;
            this.bj = bj;
            this.time = time;
        }
    }
}