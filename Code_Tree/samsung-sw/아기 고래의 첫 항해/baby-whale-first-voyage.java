import java.io.*;
import java.util.*;
public class Main {
    static int[][] map;
    static int N, r, c, d;
    static List<int[]> path; // 고래가 방문한 길
    static boolean[][] visited; // 고래의 방문 기록
    static int turn = 0;
    static boolean DEBUG = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N+1][N+1];
        path = new ArrayList<>();

        while(true){
            turn++;
            int prev = path.size();
            // System.out.println("prev" + prev);

            // 1. 인접 탐험
            moveWhale();
            print("고래 이동");

            // 2. 가장 가까운 바다로 이동
            findClosest();
            print("가장 가까운 바다");

            // System.out.println("now" + path.size());
            if(prev == path.size()) break; // 방문할 곳이 없는 경우 그만함.
        }


        // 출력
        StringBuilder sb = new StringBuilder();
        for(int[] p : path){
            sb.append(p[0] + " " + p[1]).append("\n");
        }
        System.out.print(sb);
    }

    static void findClosest(){
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r, c, 0, 3}); // r, c, 횟수, 방향
        // 좌 하 우 상
        int[][] dir = new int[][]{{0, -1, 3}, {1, 0, 2}, {0, 1, 4}, {-1, 0, 1}};

        boolean[][] checked = new boolean[N+1][N+1];
        checked[r][c] = true;

        int best = Integer.MAX_VALUE;
        int bestR = Integer.MAX_VALUE;
        int bestC = Integer.MAX_VALUE;
        int bestD = -1;

        while(!q.isEmpty()){
            int[] cur = q.poll();

            if(!visited[cur[0]][cur[1]]){
                if(cur[0] < bestR){
                    bestR = cur[0];
                    bestC = cur[1];
                    bestD = cur[3];
                }else if(cur[0] == bestR && cur[1] < bestC){
                    bestC = cur[1];
                    bestD = cur[3];
                }
            }

            for(int i = 0; i < 4; i++){
                int nextR = cur[0] + dir[i][0];
                int nextC = cur[1] + dir[i][1];

                if(isOutside(nextR, nextC) || checked[nextR][nextC]) continue;
                if(map[nextR][nextC] == 1) continue;

                checked[nextR][nextC] = true;

                // 고래가 이미 visited 한 곳을 지나갈 수도 있어야함.
                // 하지만, 가장 가까운 거리보다 먼 곳은 갈 필요가 없음.
                
                // 고래가 갈 수 있는 최적의 거리를 갱신
                if(!visited[nextR][nextC] && cur[2]+1 <= best){
                    best = cur[2] + 1;
                }
                if(cur[2]+1 <= best){
                    q.add(new int[]{nextR, nextC, cur[2]+1, dir[i][2]});
                }
            }
        }

        if((bestR == r && bestC == c) || bestR == Integer.MAX_VALUE) return;

        r = bestR;
        c = bestC;
        d = bestD;
    }

    static void moveWhale(){
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r, c});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int[][] dir = getDir(d);
            
            if(!visited[cur[0]][cur[1]]){
                visited[cur[0]][cur[1]] = true;
                path.add(new int[]{cur[0], cur[1]});
            }
            
            for(int i = 0; i < 4; i++){
                int nextR = cur[0] + dir[i][0];
                int nextC = cur[1] + dir[i][1];
                if(isOutside(nextR, nextC) || map[nextR][nextC] == 1) continue;
                if(visited[nextR][nextC]) continue;

                d = dir[i][2];
                r = nextR;
                c = nextC;
                q.add(new int[]{r, c});
                break; // 해당 방향으로 결정 되었으면 그냥 간다.
            }
        }
    }

    static void print(String title){
        if(!DEBUG) return;
        StringBuilder sb = new StringBuilder();
        sb.append("=== turn : " + turn + " == " + title + "\n");
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(map[i][j] == 1) sb.append("@ ");
                else if(r == i && c == j){
                    sb.append("W "); continue;
                }
                else if(visited[i][j]) sb.append("v "); 
                else sb.append(". ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static int[][] getDir(int dir){
        // 다음 방향 : (현재 방향 +(i)) % 5
        if(dir == 1){ // 상, 좌, 우, 하
            return new int[][]{{-1, 0, 1}, {0, -1, 3}, {0, 1, 4}, {1, 0, 2}};
        }else if(dir == 2){ // 하, 우, 좌, 상
            return new int[][]{{1, 0, 2}, {0, 1, 4}, {0, -1, 3}, {-1, 0, 1}};
        }else if(dir == 3){ // 좌, 하, 상, 우
            return new int[][]{{0, -1,  3}, {1, 0, 2}, {-1, 0, 1}, {0, 1, 4}};
        }else{ // 우, 상, 하, 좌 
            return new int[][]{{0, 1, 4}, {-1, 0, 1}, {1, 0, 2}, {0, -1, 3}};
        }
    }

    static boolean isOutside(int r, int c){
        return (r <= 0 || r > N || c <= 0 || c > N);
    }




}