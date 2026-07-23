import java.util.*;

class Solution {
    static List<Integer>[] wires;
    static int n;

    public int solution(int N, int[][] wireInfo) {
        n = N;
        wires = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            wires[i] = new ArrayList<>();
        }

        for (int[] wire : wireInfo) {
            int s = wire[0];
            int e = wire[1];
            wires[s].add(e);
            wires[e].add(s);
        }

        int answer = Integer.MAX_VALUE;

        // 전선을 하나씩 끊어보면서 한쪽 트리 크기를 계산
        for (int[] wire : wireInfo) {
            int s = wire[0];
            int e = wire[1];

            boolean[] visited = new boolean[n + 1];
            visited[s] = true;
            visited[e] = true; // e 방향으로는 못 가게 막아버림 (끊은 것처럼)

            int count = dfs(s, visited, s, e);

            int diff = Math.abs(count - (n - count));
            answer = Math.min(answer, diff);
        }

        return answer;
    }

    // s에서 시작해서 (s-e) 간선을 제외하고 도달 가능한 노드 개수 반환
    static int dfs(int cur, boolean[] visited, int s, int e) {
        int count = 1;
        for (int next : wires[cur]) {
            if (cur == s && next == e) continue; // 끊은 간선은 건너뜀
            if (cur == e && next == s) continue;
            if (visited[next]) continue;

            visited[next] = true;
            count += dfs(next, visited, s, e);
        }
        return count;
    }
}