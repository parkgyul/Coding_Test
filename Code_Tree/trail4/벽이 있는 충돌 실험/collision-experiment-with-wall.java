import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringTokenizer st;

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] bi = new int[M + 1];
            int[] bj = new int[M + 1];
            int[] bd = new int[M + 1];
            boolean[] alive = new boolean[M + 1];

            for (int k = 1; k <= M; k++) {
                st = new StringTokenizer(br.readLine());
                int i = Integer.parseInt(st.nextToken()) - 1;
                int j = Integer.parseInt(st.nextToken()) - 1;
                char c = st.nextToken().charAt(0);

                int d = -1;
                switch(c){
                    case 'L' : d = 0; break;
                    case 'R' : d = 1; break;
                    case 'U' : d = 2; break;
                    case 'D' : d = 3; break;
                }
                bi[k] = i; bj[k] = j; bd[k] = d;
                alive[k] = true;
            }

            int[] board = new int[N * N]; // 1차원 보드, 전체 초기화 안 함
            int[] ni = new int[M + 1];
            int[] nj = new int[M + 1];
            int[] used = new int[M + 1];  // 이번 라운드에 값을 쓴 칸 목록

            int aliveCount = M;

            // 2번: 공이 1개 이하면 더 이상 충돌 불가 -> 즉시 종료
            for (int round = 0; round < 2 * N && aliveCount > 1; round++) {
                int usedCnt = 0;

                // 1단계: 이동 후 좌표 계산 + 벽 충돌 처리
                for (int k = 1; k <= M; k++) {
                    if (!alive[k]) continue;
                    int dir = bd[k];
                    int x = bi[k] + dx[dir];
                    int y = bj[k] + dy[dir];
                    if (x < 0 || y < 0 || x >= N || y >= N) {
                        dir ^= 1;
                        bd[k] = dir;
                        x = bi[k];
                        y = bj[k];
                    }
                    ni[k] = x;
                    nj[k] = y;
                }

                // 2단계: 같은 칸에 도착한 공들끼리 충돌 처리
                for (int k = 1; k <= M; k++) {
                    if (!alive[k]) continue;
                    int code = ni[k] * N + nj[k];
                    int cur = board[code];

                    if (cur == -1) {
                        // 이미 그 칸에서 충돌 발생 -> 이 공도 제거
                        alive[k] = false;
                        aliveCount--;
                    } else if (cur != 0) {
                        alive[cur] = false;
                        alive[k] = false;
                        aliveCount -= 2;
                        board[code] = -1; // 이 칸은 이미 used에 등록되어 있음
                    } else {
                        board[code] = k;
                        used[usedCnt++] = code;
                    }
                }

                // 3단계: 좌표 갱신
                for (int k = 1; k <= M; k++) {
                    if (alive[k]) {
                        bi[k] = ni[k];
                        bj[k] = nj[k];
                    }
                }

                // 1번: 전체 fill 대신, 이번 라운드에 사용한 칸만 0으로 복구
                for (int t = 0; t < usedCnt; t++) {
                    board[used[t]] = 0;
                }
            }

            sb.append(aliveCount).append('\n');
        }
        System.out.print(sb);
    }
}