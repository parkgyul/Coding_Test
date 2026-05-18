import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] di, dj;
    static int[] selected;
    static int[][] dist;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        di = new int[N];
        dj = new int[N];
        dist = new int[N][N];
        selected = new int[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            di[i] = Integer.parseInt(st.nextToken());
            dj[i] = Integer.parseInt(st.nextToken());
        }

        min = Integer.MAX_VALUE;

        getDists();

        dfs(0, 0, 0);

        System.out.print(min);
    }

    static void getDists() {
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                dist[i][j] = getDist(di[i], dj[i], di[j], dj[j]);
                dist[j][i] = dist[i][j];
            }
        }
    }

    static void dfs(int depth, int index, int maxDist) {
        if (maxDist >= min) {
            return;
        }

        if (depth == M) {
            min = Math.min(min, maxDist);
            return;
        }

        int need = M - depth;

        for (int i = index; i <= N - need; i++) {
            selected[depth] = i;

            int newMaxDist = maxDist;

            for (int j = 0; j < depth; j++) {
                newMaxDist = Math.max(newMaxDist, dist[selected[j]][i]);
            }

            dfs(depth + 1, i + 1, newMaxDist);
        }
    }

    static int getDist(int si, int sj, int ei, int ej) {
        return (si - ei) * (si - ei) + (sj - ej) * (sj - ej);
    }
}