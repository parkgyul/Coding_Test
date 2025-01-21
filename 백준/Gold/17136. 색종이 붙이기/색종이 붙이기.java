import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    private static int[][] paper = new int[10][10];
    private static int[] availablePapers = {0, 5, 5, 5, 5, 5}; // 각 크기별 색종이 개수
    private static int minCount = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력받기
        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // DFS로 최소 색종이 개수 계산
        dfs(0, 0, 0);

        // 결과 출력
        System.out.println(minCount == Integer.MAX_VALUE ? -1 : minCount);
    }

    private static void dfs(int x, int y, int count) {
        // 이미 최소값보다 많은 색종이를 사용한 경우 종료
        if (count >= minCount) {
            return;
        }

        // 끝까지 탐색한 경우
        if (x == 10) {
            minCount = Math.min(minCount, count);
            return;
        }

        // 다음 행으로 이동
        if (y == 10) {
            dfs(x + 1, 0, count);
            return;
        }

        // 현재 칸이 0이면 다음 칸으로 이동
        if (paper[x][y] == 0) {
            dfs(x, y + 1, count);
            return;
        }

        // 1인 경우, 가능한 모든 크기의 색종이 사용 시도
        for (int size = 1; size <= 5; size++) {
            if (canPlace(x, y, size)) {
                placePaper(x, y, size, 0); // 색종이 붙이기
                availablePapers[size]--;
                dfs(x, y + 1, count + 1);
                placePaper(x, y, size, 1); // 색종이 떼기
                availablePapers[size]++;
            }
        }
    }

    private static boolean canPlace(int x, int y, int size) {
        // 색종이를 붙일 수 있는지 확인
        if (availablePapers[size] == 0) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int nx = x + i;
                int ny = y + j;
                if (nx >= 10 || ny >= 10 || paper[nx][ny] == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void placePaper(int x, int y, int size, int value) {
        // 색종이를 붙이거나 떼는 함수
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                paper[x + i][y + j] = value;
            }
        }
    }
}
