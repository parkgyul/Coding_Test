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
            for(int j =0 ; j < M; j++){
                char ch = str.charAt(j);
                board[i][j] = ch;

                if(ch == 'R'){
                    startRi = i;
                    startRj = j;
                    board[i][j] = '.';
                }else if(ch == 'B'){
                    startBi = i;
                    startBj = j;
                    board[i][j] = '.';
                }
            }
        }

        System.out.print(bfs(startRi, startRj, startBi, startBj));
    }

    public static int bfs(int startRi, int startRj, int startBi, int startBj){
        Queue<State> q= new LinkedList<>();
        q.add(new State(startRi, startRj, startBi, startBj, 0));
        visited[startRi][startRj][startBi][startBj] = true;

        while(!q.isEmpty()){
            State cur = q.poll();

            if(cur.depth >= 10) continue;

            for(int dir = 0; dir < 4; dir++){
                MoveResult red = move(cur.ri, cur.rj, dir);
                MoveResult blue = move(cur.bi, cur.bj, dir);

                if(blue.inHole) continue;
                if(red.inHole) return cur.depth + 1;

                int nri = red.i;
                int nrj = red.j;
                int nbi = blue.i;
                int nbj = blue.j;

                if(nri == nbi && nrj == nbj){
                    if(red.count > blue.count){
                        nri -= dx[dir];
                        nrj -= dy[dir];
                    }else{
                        nbi -= dx[dir];
                        nbj -= dy[dir];
                    }
                }

                if(!visited[nri][nrj][nbi][nbj]){
                    visited[nri][nrj][nbi][nbj] = true;
                    q.add(new State(nri, nrj, nbi, nbj, cur.depth+1));
                }
            }
        }
        return -1;
    }

    public static MoveResult move(int i, int j, int dir){
        int ni = i;
        int nj = j;
        int count = 0;

        while(true){
            int ti = ni + dx[dir];
            int tj = nj + dy[dir];

            if(board[ti][tj] == '#') break;

            ni = ti;
            nj = tj;
            count++;

            if(board[ni][nj] == 'O'){
                return new MoveResult(ni, nj, count, true);
            }
        }

        return new MoveResult(ni, nj, count, false);
    }

    public static class MoveResult{
        int i, j, count;
        boolean inHole;

        public MoveResult(int i, int j, int count, boolean inHole){
            this.i = i;
            this.j = j;
            this.count = count;
            this.inHole = inHole;
        }
    }

    public static class State{
        int ri, rj, bi, bj, depth;

        public State(int ri, int rj, int bi, int bj, int depth){
            this.ri = ri;
            this.rj = rj;
            this.bi = bi;
            this.bj = bj;
            this.depth = depth;
        }
    }
}