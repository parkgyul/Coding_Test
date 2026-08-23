import java.util.*;

class Solution {

    private static class Node {
        int from;
        int to;

        Node(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    static boolean[] visited;
    static List<Integer>[] graph;

    public boolean solution(int n, int[][] path, int[][] order) {

        visited = new boolean[n];

        // ============================================
        // 1. 인접 리스트 만들기
        // ============================================

        graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] p : path) {
            int from = p[0];
            int to = p[1];

            graph[from].add(to);
            graph[to].add(from);
        }

        // ============================================
        // 2. 선행 조건 만들기
        //
        // need[B] = A
        // => B를 방문하려면 A를 먼저 방문해야 함
        // ============================================

        int[] need = new int[n];

        Arrays.fill(need, -1);

        for (int[] o : order) {
            int before = o[0];
            int after = o[1];

            need[after] = before;
        }

        // 0번 방을 방문하려면 다른 방을 먼저 방문해야 한다면
        // 애초에 탐험 시작이 불가능
        if (need[0] != -1) {
            return false;
        }

        // ============================================
        // 3. waiting
        //
        // waiting[A] = B
        // => A를 방문하면 B를 방문할 수 있음
        //
        // 예:
        // 4 -> 1
        //
        // 1을 발견했는데 4를 아직 방문하지 않았다면
        //
        // waiting[4] = 1
        //
        // 나중에 4를 방문하면 1을 다시 처리
        // ============================================

        int[] waiting = new int[n];

        Arrays.fill(waiting, -1);

        // ============================================
        // 4. BFS 시작
        // ============================================

        Queue<Integer> q = new LinkedList<>();

        visited[0] = true;
        q.offer(0);

        int count = 1;

        while (!q.isEmpty()) {

            int cur = q.poll();

            // ========================================
            // 현재 방에서 갈 수 있는 방 확인
            // ========================================

            for (int next : graph[cur]) {

                // 이미 방문한 방이면 무시
                if (visited[next]) {
                    continue;
                }

                // ====================================
                // next를 방문하기 위한 선행 방이 있는 경우
                // ====================================

                if (need[next] != -1) {

                    int before = need[next];

                    // 선행 방을 아직 방문하지 않았다면
                    // next를 지금 방문하면 안 됨
                    if (!visited[before]) {

                        // 나중에 before를 방문했을 때
                        // next를 처리할 수 있도록 저장
                        waiting[before] = next;

                        continue;
                    }
                }

                // ====================================
                // 선행 조건이 없거나
                // 선행 방을 이미 방문한 경우
                // ====================================

                visited[next] = true;
                count++;

                q.offer(next);

                // ====================================
                // next를 방문함으로써
                // waiting 중인 방이 풀렸는지 확인
                // ====================================

                if (waiting[next] != -1) {

                    int waitingRoom = waiting[next];

                    // 이제 waitingRoom을 방문할 수 있음
                    visited[waitingRoom] = true;
                    count++;

                    q.offer(waitingRoom);

                    // 다시 처리하지 않도록 제거
                    waiting[next] = -1;
                }
            }
        }

        // 모든 방을 방문했다면 성공
        return count == n;
    }
}