import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 격자 크기
        int M = Integer.parseInt(st.nextToken()); // 구슬 개수
        int T = Integer.parseInt(st.nextToken()); // 시간
        int K = Integer.parseInt(st.nextToken()); // 최대 구슬 개수

        List<Ball>[][] map = new ArrayList[N][N];

        int remain = M;

        int[] dx = {0, -1, 1, 0, 0};
        int[] dy = {0, 0, 0, -1, 1};

        for(int i =0 ; i < N; i++){
            for(int j = 0; j < N; j++){
                map[i][j] = new ArrayList<>();
            }
        }

        for(int a = 0; a < M; a++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            char charD = st.nextToken().charAt(0);
            int v = Integer.parseInt(st.nextToken());

            int d = 0;
            switch(charD){
                case 'U' : d = 1; break; 
                case 'D' : d = 2; break;
                case 'L' : d = 3; break;
                case 'R' : d = 4; break;
            }

            map[r][c].add(new Ball(a, v, d));
        }


        while(T-- > 0){
            List<Ball>[][] newMap = new ArrayList[N][N];

            for(int i =0 ; i < N; i++){
                for(int j = 0; j < N; j++){
                    newMap[i][j] = new ArrayList<>();
                }
            }

            for(int i =0 ; i < N; i++){
                for(int j = 0; j < N; j++){
                    List<Ball> list = map[i][j];

                    for(Ball ball : list){
                        int v = ball.v;
                        int d = ball.dir;
                        int si = i, sj = j;
                        for (int step = 0; step < v; step++) {
                            int ni = si + dx[d];
                            int nj = sj + dy[d];
                            if (ni < 0 || ni >= N || nj < 0 || nj >= N) {
                                d = (d % 2 == 1) ? d+1 : d-1;
                                ni = si + dx[d];
                                nj = sj + dy[d];
                            }

                            si = ni;
                            sj = nj;
                        }

                        newMap[si][sj].add(new Ball(ball.num, v, d));
                    }
                }
            }

            for(int i =0 ; i < N; i++){
                for(int j = 0; j < N; j++){
                    List<Ball> list = newMap[i][j];

                    if(list.size() <= K) continue;

                    list.sort((a, b) -> {
                        if(a.v == b.v) return b.num - a.num;
                        return b.v - a.v;
                    });

                    remain -= (list.size() - K);

                    list.subList(K, list.size()).clear();
                }
            }

            map = newMap;
        }

        System.out.println(remain);
    }

    static class Ball{
        int num, v, dir;

        Ball(int num, int v, int dir){
            this.num = num;
            this.v = v;
            this.dir = dir;
        }
    }
}