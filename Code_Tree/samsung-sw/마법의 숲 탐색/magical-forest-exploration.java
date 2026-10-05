import java.io.*;
import java.util.*;
public class Main {
    static boolean DEBUG = false;
    static int R, C, K;
    static int[][] forest;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static List<int[]> list;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        forest = new int[R+1][C+1];
        
        int num = 1;
        list = new ArrayList<>();
        int result = 0;
        while(K-- > 0){
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken()); // 골렘 출발 열
            int d = Integer.parseInt(st.nextToken()); // 출구 방향 정보

            int[] info = go(c, d);
            
            if(info[0] <= 1){
                list = new ArrayList<>();
                forest = new int[R+1][C+1];
                num = 1;
                continue;
            }
            
            putGol(info, num++);
            print("putGol 다음");
            // i = num-1에 출구 정보 저장.
            list.add(new int[]{info[0]+dr[info[2]], info[1]+dc[info[2]]});
            result += findFinalDest(info);
        }

        System.out.print(result);
    }

    static int findFinalDest(int[] info){
        int finalRow = info[0]+1;
        Queue<int[]> q= new ArrayDeque<>();
        q.add(new int[]{info[0], info[1], forest[info[0]][info[1]]}); // r, c, num
        boolean[][] visited = new boolean[R+1][C+1];
        visited[info[0]][info[1]] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();

            if(finalRow < cur[0]) finalRow = cur[0];

            for(int i = 0; i < 4; i++){
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                if(isOutside(nr, nc) || visited[nr][nc]) continue;
                if(forest[nr][nc] == 0) continue;

                int[] exitInfo = list.get(cur[2] - 1); // 출구인지 확인 하기 위함.
                
                // 1. 같은 골램이면 그냥 갈 수 있음.
                // 2. 출구라면 같은 골램이 아니라도 갈 수 있음.
                if(forest[nr][nc] == cur[2] || (cur[0] == exitInfo[0] && cur[1] == exitInfo[1])){
                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc, forest[nr][nc]});
                }
            }
        }

        return finalRow;
    }

    static boolean isOutside(int r, int c){
        return (r <= 0 || r > R || c <= 0 || c > C);
    }

    static void putGol(int[] info, int num){
        int r = info[0];
        int c = info[1];

        forest[r][c] = num;

        for(int i = 0; i < 4; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];
            forest[nr][nc] = num;
        }
    }

    static int[] go(int c, int d){
        int r = -1;
        while(true){
             // 1. 남쪽 이동
            if(canPlace(r+1, c)){
                r++;
            }else if(canPlace(r, c-1) && canPlace(r+1, c-1)){ // 2. 서쪽 이동
                r++; c--;
                d = (d+3)%4;
            }else if(canPlace(r, c+1) && canPlace(r+1, c+1)){ // 3. 동쪽 이동
                r++; c++;
                d = (d+1)%4;
            }else break;
        }

        return new int[]{r, c, d};
    }

    static boolean isEmpty(int r, int c){
        if(c <= 0 || c > C || r > R) return false;
        if(r <= 0) return true;
        return forest[r][c] == 0;
    }

    static boolean canPlace(int r, int c){
        return isEmpty(r, c-1) && isEmpty(r, c) && isEmpty(r-1, c) && isEmpty(r+1, c) && isEmpty(r, c+1); 
    }

    static void print(String title){
        if(!DEBUG) return;
        StringBuilder sb = new StringBuilder();
        sb.append("== title : " + title + "== K : " + K + "\n");
        for(int i = 1; i <= R; i++){
            for(int j = 1; j <= C; j++){
                sb.append(forest[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}