import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int[] top, left, hh, ww;   // 택배 번호로 바로 접근
    static boolean[] alive;
    static List<Integer> ids = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] in = new int[M][4];
        int maxK = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int t = 0; t < 4; t++) in[i][t] = Integer.parseInt(st.nextToken());
            maxK = Math.max(maxK, in[i][0]);
        }

        map = new int[N + 1][N + 1];
        top = new int[maxK + 1]; left = new int[maxK + 1];
        hh = new int[maxK + 1];  ww = new int[maxK + 1];
        alive = new boolean[maxK + 1];

        for (int[] p : in) {
            int k = p[0];
            hh[k] = p[1]; ww[k] = p[2]; left[k] = p[3];
            alive[k] = true;
            ids.add(k);
            drop(k, hh[k]);              // 바닥 행이 h인 상태(맨 위)에서 낙하
        }

        StringBuilder sb = new StringBuilder();
        boolean isLeft = true;
        for (int t = 0; t < M; t++) {
            int picked = pickOne(isLeft);
            erase(picked);
            alive[picked] = false;
            pull();
            sb.append(picked).append('\n');
            isLeft = !isLeft;
        }
        System.out.print(sb);
    }

    // 하차 가능한 택배 중 번호 최소
    static int pickOne(boolean isLeft) {
        int[] cnt = new int[alive.length];
        for (int i = 1; i <= N; i++) {
            if (isLeft) {
                for (int j = 1; j <= N; j++) if (map[i][j] != 0) { cnt[map[i][j]]++; break; }
            } else {
                for (int j = N; j >= 1; j--) if (map[i][j] != 0) { cnt[map[i][j]]++; break; }
            }
        }
        for (int k = 1; k < alive.length; k++) {
            if (alive[k] && cnt[k] == hh[k]) return k;   // 번호 오름차순이라 처음 걸린 게 최소
        }
        return -1;
    }

    // 남은 택배를 바닥에 가까운 순서로 다시 떨어뜨리기
    static void pull() {
        List<Integer> list = new ArrayList<>();
        for (int k : ids) if (alive[k]) list.add(k);
        list.sort((a, b) -> (top[b] + hh[b]) - (top[a] + hh[a]));

        for (int k : list) {
            erase(k);
            drop(k, top[k] + hh[k] - 1);
        }
    }

    // 바닥 행 startBottom에서 시작해 내려갈 수 있을 만큼 내려가서 놓기
    static void drop(int k, int startBottom) {
        int r = startBottom;
        while (canDown(r + 1, left[k], ww[k])) r++;
        top[k] = r - hh[k] + 1;
        fill(k, k);
    }

    static boolean canDown(int r, int c, int w) {
        if (r > N) return false;
        for (int j = c; j < c + w; j++) if (map[r][j] != 0) return false;
        return true;
    }

    static void erase(int k) { fill(k, 0); }

    static void fill(int k, int val) {
        for (int i = top[k]; i < top[k] + hh[k]; i++)
            for (int j = left[k]; j < left[k] + ww[k]; j++)
                map[i][j] = val;
    }
}