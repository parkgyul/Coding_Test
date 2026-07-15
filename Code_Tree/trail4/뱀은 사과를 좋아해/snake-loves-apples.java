import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[][] apple = new boolean[N+1][N+1];
        boolean[][] snake = new boolean[N+1][N+1];
        

        for(int i = 0; i < M; i++){ // 사과 입력 받기
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            apple[r][c] = true;
        }

        int time = 0;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int[] turnDir = new int[K];
        int[] turnCnt = new int[K];

        for(int i =0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            char chD = st.nextToken().charAt(0);

            int d = -1;

            switch(chD){
                case 'R' : d = 3; break;
                case 'U' : d = 0; break;
                case 'L' : d = 2; break;
                case 'D' : d = 1; break; 
            }

            int cnt = Integer.parseInt(st.nextToken());

            turnDir[i] = d;
            turnCnt[i] = cnt;
        }
        if(K == 0){
            System.out.print(time);
            return;
        } 

        int idx = 0;
        int idxCnt = 0;
        Deque<Point> q = new ArrayDeque<>();
        q.addFirst(new Point(1, 1));
        int d = turnDir[0];

        while(true){
            Point head = q.getFirst();
            int ni, nj;

            if(idx < K && idxCnt == turnCnt[idx]){
                idx++;
                idxCnt = 0;
                if(idx == K) break;
                d = turnDir[idx];
                continue;
            }

            time++;

            ni = head.i + dx[d];
            nj = head.j + dy[d];

            Point tail = q.getLast();

            if(ni < 1 || nj < 1 || ni > N || nj > N || (snake[ni][nj] && !(tail.i == ni&&tail.j==nj))){
                break;
            }

            if(apple[ni][nj]){
                q.addFirst(new Point(ni, nj));
                snake[ni][nj] = true;
                apple[ni][nj] = false;
            }else{
                q.addFirst(new Point(ni, nj));
                snake[ni][nj] = true;

                q.pollLast();
                snake[tail.i][tail.j] = false;
            }
            idxCnt++;
        }

        System.out.print(time);
    }

    static class Point{
        int i, j;
        Point(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
}