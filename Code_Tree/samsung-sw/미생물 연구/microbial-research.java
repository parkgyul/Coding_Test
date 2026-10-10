import java.io.*;
import java.util.*;
public class Main {
    static int N, Q;
    static int[][] map;
    static int idx;
    static StringBuilder result;
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        result = new StringBuilder();

        idx = 1;
        while(Q-- > 0){
            st = new StringTokenizer(br.readLine());
            // 1-1. 미생물 투입
            inject(st, idx);

            // 1-2. 두 개로 쪼개진 무리 없애기
            removeSplited(idx);

            // print("1끝");
            // 2. 배양 용기 이동
            moveMicro(idx);
            // print("끝");

            // 3. 실험 결과 기록
            getScore(idx);
            idx++;
        }

        System.out.print(result);

    }

    static void getScore(int maxIdx){
        boolean[][] neighbor = new boolean[maxIdx+1][maxIdx+1];
        int[] area = new int[maxIdx+1];

        long sum = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int m = map[i][j];
                if(m == 0) continue;

                area[m]++;

                // 위
                if(i < N-1 && map[i+1][j] != m){
                    int n = map[i+1][j];
                    neighbor[m][n] = true;
                    neighbor[n][m] = true;
                }

                // 오른쪽
                if(j < N-1 && map[i][j+1] != m){
                    int n = map[i][j+1];
                    neighbor[m][n] = true;
                    neighbor[n][m] = true;
                }
            }
        }

        for(int i = 1; i <= maxIdx; i++){
            for(int j = i+1; j <= maxIdx; j++){
                if(neighbor[i][j]){
                    sum += (area[i]*area[j]);
                }
            }
        }

        result.append(sum).append("\n");
    }

    static void moveMicro(int maxIdx){
        List<int[]>[] cells = new ArrayList[maxIdx+1];
        for(int i = 1; i <= maxIdx; i++){
            cells[i] = new ArrayList<>();
        }

        List<Integer> ids = new ArrayList<>();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int m = map[i][j];

                if(m == 0) continue;

                cells[m].add(new int[]{i, j});
                if(!ids.contains(m)) ids.add(m);
            }
        }

        // 영역 넓은 무리 -> 가장 먼저 투입(id가 작은 순)
        ids.sort((a, b)->{
           if(cells[a].size() != cells[b].size()) return cells[b].size() - cells[a].size();
           return a - b;
        });

        int[][] newMap = new int[N][N];

        for(int id : ids){
            List<int[]> ce = cells[id];

            int minX = Integer.MAX_VALUE;
            int minY = Integer.MAX_VALUE;

            for(int[] c : ce){
                minX = Math.min(minX, c[0]);
                minY = Math.min(minY, c[1]);
            }

            boolean isPlaced = false;
            for(int i = 0; i < N && !isPlaced; i++){
                for(int j = 0; j < N && !isPlaced; j++){
                    int ox = i - minX;
                    int oy = j - minY;

                    if(canPlace(newMap, ce, ox, oy)){
                        for(int[] c : ce){ newMap[c[0]+ox][c[1]+oy] = id; }
                        isPlaced = true;
                    }
                }
            }
        }

        map = newMap;
    }

    static boolean canPlace(int[][] map, List<int[]> ce, int ox, int oy){
        for(int[] c : ce){
            int x = c[0] + ox;
            int y = c[1] + oy;
            if(isOutside(x, y)) return false;
            if(map[x][y] != 0) return false;
        }

        return true;
    }

    static void print(String title){
        StringBuilder sb = new StringBuilder();
        sb.append("==" + title + "==\n");
        for(int y = N-1; y >= 0; y--){
            for(int x = 0; x < N; x++){
                sb.append(map[x][y] + " ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    // 미생물 투입
    static void inject(StringTokenizer st, int idx){
        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());

        for(int i = r1; i < r2; i++){
            for(int j = c1; j < c2; j++){
                map[i][j] = idx;
            }
        }
    }

    // 둘로 쪼개어진 무리 삭제
    static void removeSplited(int maxIdx){
        boolean[][] visited = new boolean[N][N];
        int[] area = new int[maxIdx+1];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] == 0 || visited[i][j]) continue;

                bfs(i, j, visited);
                area[map[i][j]]++;
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int a = map[i][j];

                // 아무것도 없는 공간 or 무리가 1개인 미생물
                if(a == 0 || area[a] == 1) continue;

                map[i][j] = 0;
            }
        }
    }

    static void bfs(int r, int c, boolean[][] visited){
        int num = map[r][c];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r, c});
        visited[r][c] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();

            for(int d = 0; d < 4; d++){
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];

                if(isOutside(nr, nc)) continue;
                if(visited[nr][nc] || map[nr][nc] != num) continue;

                visited[nr][nc] = true;
                q.add(new int[]{nr, nc});
            }
        }
    }

    static boolean isOutside(int r, int c){
        return r < 0 || r >= N || c < 0 || c >= N;
    }
}