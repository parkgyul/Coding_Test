import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static int N;
    static char[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;

        arr = new char[N][N];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        // visited[i][j][dir] : (i,j) 위치에서 dir 방향으로 진행을 시도한 적이 있는지
        boolean[][][] visited = new boolean[N][N][4];

        int dir = 0; // 처음엔 오른쪽을 바라봄
        int time = 0;

        while (true) {
            // 같은 위치, 같은 방향으로 이미 진행한 적이 있다면 탈출 불가능
            if (visited[x][y][dir]) {
                System.out.print(-1);
                return;
            }
            visited[x][y][dir] = true;

            int ni = x + dx[dir];
            int nj = y + dy[dir];

            // Step 1: 바라보는 방향으로 이동이 불가능하면 반시계 방향으로 90도 회전
            if (wallExist(ni, nj)) {
                dir = (dir -1 + 4) % 4;
                continue;
            }

            // Step 2 - Case 1: 바로 앞이 격자 밖이면 탈출
            if (!inRange(ni, nj)) {
                x = ni;
                y = nj;
                time++;
                break;
            }

            // Step 2 - Case 2 & 3: 이동 가능한 경우
            int rdir = (dir + 1) % 4;
            int rx = ni + dx[rdir];
            int ry = nj + dy[rdir];

            if (wallExist(rx, ry)) {
                // Case 2: 오른쪽에 벽이 있으면 한 칸만 이동
                x = ni;
                y = nj;
                time++;
            } else {
                // Case 3: 오른쪽에 벽이 없으면 두 칸 이동 후 시계방향으로 회전
                x = rx;
                y = ry;
                dir = (dir + 1) % 4;
                time += 2;
            }
        }

        System.out.print(time);
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static boolean wallExist(int x, int y) {
        return inRange(x, y) && arr[x][y] == '#';
    }
}