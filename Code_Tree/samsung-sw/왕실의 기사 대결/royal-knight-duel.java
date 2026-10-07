import java.io.*;
import java.util.*;

public class Main {
    static boolean DEBUG = true;
    static int L, N, Q;
    static int[][] map;
    static int[][] disorder;
    static int[] R, C, H, W, K, D;
    static boolean[] killed;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        map = new int[L+1][L+1];
        disorder = new int[L+1][L+1];

        for(int i = 1; i <= L; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= L; j++){
                disorder[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        R = new int[N+1];
        C = new int[N+1];
        H = new int[N+1];
        W = new int[N+1];
        K = new int[N+1];
        D = new int[N+1];
        killed = new boolean[N+1];

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            R[i] = r;
            C[i] = c;
            H[i] = h;
            W[i] = w;
            K[i] = k;

            // 기사 배치하기
            place(i, i);
        }

        while(Q-- > 0){
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            if(killed[id]) continue;
            
            List<Integer> moved = getPushed(id, d);
            if(moved == null) continue;  

            move(moved, id, d);
        }

        int damage = 0;

        for(int i = 1; i <= N; i++){
            if(killed[i]) continue;
            damage += D[i];
        }

        System.out.print(damage);

    }

    static void move(List<Integer> moved, int id, int d){
        for (int idx : moved) {
            R[idx] += dr[d];
            C[idx] += dc[d];
        }

        for (int idx : moved) {
            if (idx == id) continue;

            addDamage(idx);

            if (K[idx] <= D[idx]) killed[idx] = true;
        }
    }

    static List<Integer> getPushed(int id, int d){
        List<Integer> moved = new ArrayList<>();
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N+1];

        q.add(id);
        visited[id] = true;

        while(!q.isEmpty()){
            int cur = q.poll();
            moved.add(cur);

            int nr = R[cur] + dr[d];
            int nc = C[cur] + dc[d];

            for(int i = nr; i < nr + H[cur]; i++){
                for(int j = nc; j < nc + W[cur]; j++){
                    if(i < 1 || i > L || j < 1 || j > L) return null;
                    if(disorder[i][j] ==2) return null;
                }
            }

            for(int next = 1; next <= N; next++){
                if(visited[next] || killed[next]) continue;
                if(overlap(nr, nc, H[cur], W[cur], R[next], C[next], H[next], W[next])){
                    visited[next] = true;
                    q.add(next);
                }

            }
        }

        return moved;
    }
    
    static boolean overlap(int r1, int c1, int h1, int w1, int r2, int c2, int h2, int w2) {
        return r1 <= r2 + h2 - 1 && r2 <= r1 + h1 - 1
                && c1 <= c2 + w2 - 1 && c2 <= c1 + w1 - 1;
    }

    static void addDamage(int id){
        if(killed[id]) return;
        int damage = 0;
        for(int i = R[id]; i < R[id] + H[id]; i++){
            for(int j = C[id]; j < C[id] + W[id]; j++){
                if(disorder[i][j] == 1) damage++;
            }
        }

        D[id] += damage;
    }

    static void place(int id, int value){
        for(int i = R[id]; i < R[id] + H[id]; i++){
            for(int j = C[id]; j < C[id] + W[id]; j++){
                map[i][j] = value;
            }
        }
    }

    static void print(String title){
        if(!DEBUG) return;
        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= L; i++){
            for(int j = 1; j <= L; j++){
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}