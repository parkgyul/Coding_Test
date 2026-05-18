import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] di, dj;
    static int[] selected;
    static int[][] dist;
    static int min;
    static int maxDist;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        di = new int[N];
        dj = new int[N];
        dist = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            di[i] = a;
            dj[i] = b;
        }

        selected = new int[N];
        min = Integer.MAX_VALUE;

        getDists(0, 0);

        dfs(0, 0, 0);
       

        System.out.print(min);

    }

    static void getDists(int index, int depth){
        if(depth == 2){
            int a = selected[0];
            int b = selected[1];

            dist[a][b] = getDist(di[a], dj[a], di[b], dj[b]);
            return;
        }

        for(int i = index; i < N; i++){
            selected[depth] = i;
            getDists(i+1, depth+1);
        }
    }

    static void dfs(int depth, int index, int maxSum){
        if(maxSum >= min){
            return;
        }
        if(depth == M){
            min = Math.min(min, maxSum);
            return;
        }

        for(int i = index; i < N; i++){
            selected[depth] = i;
            int newMaxSum = maxSum;
            if(depth != 0){
                for(int j = depth-1; j >= 0; j--){
                    newMaxSum = Math.max(dist[selected[j]][i], newMaxSum);
                }
            }
            dfs(depth+1, i+1, newMaxSum);
        }
    }

    static int getDist(int si, int sj, int ei, int ej){
        return (si-ei)*(si-ei) + (sj-ej)*(sj-ej);
    }
}