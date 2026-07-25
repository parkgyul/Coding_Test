import java.util.*;

class Solution {
    private static final int STRAIGHT = 100;
    private static final int CORNER = 600;
    private static final int INF = Integer.MAX_VALUE;

    private final int[] dx = {-1, 0, 1, 0};
    private final int[] dy = {0, -1, 0, 1};

    public int solution(int[][] board) {
        return dijkstra(board);
    }

    private int dijkstra(int[][] board) {
        int n = board.length;

        int[][][] dist = new int[n][n][4];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dist[i][j], INF);
            }
        }

        PriorityQueue<Road> pq = new PriorityQueue<>();
        pq.add(new Road(0, 0, -1, 0));

        while (!pq.isEmpty()) {
            Road cur = pq.poll();

            int x = cur.x;
            int y = cur.y;
            int dir = cur.dir;
            int cost = cur.cost;

            if (x == n - 1 && y == n - 1) {
                return cost;
            }

            if (dir != -1 && cost > dist[x][y][dir]) {
                continue;
            }

            for (int nextDir = 0; nextDir < 4; nextDir++) {
                int nx = x + dx[nextDir];
                int ny = y + dy[nextDir];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }

                if (board[nx][ny] == 1) {
                    continue;
                }

                int nextCost;

                if (dir == -1 || dir == nextDir) {
                    nextCost = cost + STRAIGHT;
                } else {
                    nextCost = cost + CORNER;
                }

                if (nextCost < dist[nx][ny][nextDir]) {
                    dist[nx][ny][nextDir] = nextCost;
                    pq.add(new Road(nx, ny, nextDir, nextCost));
                }
            }
        }

        return -1;
    }

    private static class Road implements Comparable<Road> {
        int x;
        int y;
        int dir;
        int cost;

        Road(int x, int y, int dir, int cost) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cost = cost;
        }

        @Override
        public int compareTo(Road other) {
            return Integer.compare(this.cost, other.cost);
        }
    }
}