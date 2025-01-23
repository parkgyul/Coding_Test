import java.io.IOException;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int num;
    static int[][] arr;
    static int[][] result;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        num = Integer.parseInt(br.readLine());

        arr = new int[num][num];
        result = new int[num][num];

        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < num; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 모든 정점에서 BFS 탐색
        for (int i = 0; i < num; i++) {
            bfs(i);
        }

        // 결과 출력
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[num]; // 각 탐색마다 방문 초기화

        q.add(start);

        while (!q.isEmpty()) {
            int current = q.poll();

            for (int next = 0; next < num; next++) {
                if (arr[current][next] == 1 && !visited[next]) {
                    visited[next] = true; // 방문 체크
                    result[start][next] = 1; // 경로 기록
                    q.add(next);
                }
            }
        }
    }
}
