import java.util.Scanner;

public class Main {
    // 문제에서 주어진 상-하-좌-우 우선순위 방향 벡터
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    static int n, m, t;
    static int[][] grid;
    static int[][] countGrid; // 현재 구슬들의 위치를 기록할 격자
    static int[][] nextCountGrid; // 이동 후 구슬들의 위치를 임시 기록할 격자

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        t = sc.nextInt();

        grid = new int[n][n];
        countGrid = new int[n][n];

        // 1. 격자판 정보 입력
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        // 2. 초기 구슬 위치 입력 (1-indexed를 0-indexed로 보정)
        for (int i = 0; i < m; i++) {
            int r = sc.nextInt() - 1;
            int c = sc.nextInt() - 1;
            countGrid[r][c] = 1;
        }

        // 3. T초 동안 시뮬레이션 진행
        for (int step = 0; step < t; step++) {
            simulate();
        }

        // 4. 남아있는 구슬의 수 계산 및 출력
        int remainingMarbles = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (countGrid[i][j] == 1) {
                    remainingMarbles++;
                }
            }
        }

        System.out.println(remainingMarbles);
        sc.close();
    }

    // 1초 동안 구슬들이 이동하는 함수
    static void simulate() {
        nextCountGrid = new int[n][n];

        // 모든 칸을 돌며 구슬이 있는 경우 이동시킵니다.
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (countGrid[r][c] == 1) {
                    moveMarble(r, c);
                }
            }
        }

        // 이동이 끝난 후, 2개 이상 겹친 구슬은 소멸시킵니다.
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (nextCountGrid[r][c] >= 2) {
                    nextCountGrid[r][c] = 0;
                }
            }
        }

        // 다음 격자 상태를 현재 격자 상태로 업데이트합니다.
        for (int r = 0; r < n; r++) {
            System.arraycopy(nextCountGrid[r], 0, countGrid[r], 0, n);
        }
    }

    // (r, c)에 있는 구슬을 규칙에 따라 다음 위치로 이동시키는 함수
    static void moveMarble(int r, int c) {
        int maxVal = -1;
        int nextR = -1;
        int nextC = -1;

        // 상, 하, 좌, 우 순서대로 탐색하여 우선순위 자동 반영
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int dc_dir = c + dc[d];

            // 격자 범위를 벗어나면 패스
            if (nr < 0 || nr >= n || dc_dir < 0 || dc_dir >= n) {
                continue;
            }

            // 인접한 곳의 숫자가 현재까지 본 최댓값보다 크다면 갱신
            // (Strictly Greater '>' 를 사용하여 값이 같을 때는 먼저 나온 우선순위 방향을 유지)
            if (grid[nr][dc_dir] > maxVal) {
                maxVal = grid[nr][dc_dir];
                nextR = nr;
                nextC = dc_dir;
            }
        }

        // 이동할 위치에 구슬 카운트 누적 (동시 이동을 위해 nextCountGrid에 기록)
        nextCountGrid[nextR][nextC]++;
    }
}