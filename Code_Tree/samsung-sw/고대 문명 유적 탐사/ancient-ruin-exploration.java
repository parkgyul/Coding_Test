import java.io.*;
import java.util.*;

public class Main {
    static boolean DEBUG = false;
    static int K, M;
    static int[][] map;
    static Queue<Integer> walls;
    static StringBuilder result;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken()); // 반복 횟수
        M = Integer.parseInt(st.nextToken()); // 벽면 조각 개수

        // 지도 정보 입력
        map = new int[5][5];
        for(int i = 0; i < 5; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 5; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 벽면 정보 입력
        walls = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            walls.add(Integer.parseInt(st.nextToken()));
        }

        result = new StringBuilder();

        while(K -- > 0){
            // 1. 탐사 진행
            explore();
            print("탐사 진행");
        }

        System.out.print(result);
    }

    static void explore(){
        /**
            // 회전 하기
            1. 유물 1차 획득 가치 최대
            2. 회전 각도 최소
            3. 열 최소
            4. 행 최소
        **/
        int bestV = Integer.MIN_VALUE; // 1차 획득 가치
        int bestD = Integer.MAX_VALUE; // 회전한 각도
        int bestR = Integer.MIN_VALUE; // 행
        int bestC = Integer.MAX_VALUE; // 열

        for(int d = 1; d <= 3; d++){          // 각도
            for(int j = 1; j <= 3; j++){      // 중심 열
                for(int i = 1; i <= 3; i++){  // 중심 행
                    int value = collect(rotate(i, j, d), false);
                    
                    if(value > bestV){
                        bestV = value;
                        bestD = d;
                        bestR = i;
                        bestC = j;
                    }
                }
            }
        }

        if(bestV == 0) return;

        map = rotate(bestR, bestC, bestD);
        int sum = 0;
        while(true){
            int v = collect(map, true);
            if(v == 0) break;
            sum += v;
            fillCells(map);
        }


        result.append(sum).append(" ");
    }

    static int[][] fillCells(int[][] arr){
        for(int j = 0; j < 5; j++){
            for(int i = 4; i >= 0; i--){
                if(arr[i][j] == 0){
                    arr[i][j] = walls.poll();
                }
            }
        }

        return arr;
    }

    // r,c 기준으로 degree 만큼 돌림.
    static int[][] rotate(int r, int c, int degree){
        int[][] rotated = copyArr(map);

        for(int d = 1; d <= degree; d++){
            int[][] temp = copyArr(rotated);

            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    rotated[r-1+i][c-1+j] = temp[r-1+2-j][c-1+i];
                }
            }
        }

        return rotated;
    }

    static int[][] copyArr(int[][] arr){
        int[][] newArr = new int[5][5];

        for(int i = 0; i < 5; i++){
            newArr[i] = arr[i].clone();
        }

        return newArr;
    }

    static int collect(int[][] arr, boolean clear){
        visited = new boolean[5][5];
        int value = 0;

        for(int r = 0; r < 5; r++){
            for(int c = 0; c < 5; c++){
                if(visited[r][c] || arr[r][c] == 0) continue;

                visited[r][c] = true;
    
                Queue<int[]> q = new ArrayDeque<>();
                List<int[]> cells = new ArrayList<>();
                
                cells.add(new int[]{r, c});
                q.add(new int[]{r, c});

                while(!q.isEmpty()){
                    int[] cur = q.poll();

                    for(int i = 0; i < 4; i++){
                        int nr = cur[0] + dr[i];
                        int nc = cur[1] + dc[i];

                        if(isOutside(nr, nc)) continue;
                        if(visited[nr][nc] || arr[nr][nc] != arr[r][c]) continue;

                        visited[nr][nc] = true;
                        cells.add(new int[]{nr, nc});
                        q.add(new int[]{nr, nc});
                    }
                }

                if(cells.size() >= 3){
                    value += cells.size();
                    if(clear){
                        for(int[] cell : cells){
                            arr[cell[0]][cell[1]] = 0;
                        }
                    }
                }
            }
        }


        return value;
    }


    static boolean isOutside(int r, int c){
        return (r < 0 || r >= 5 || c < 0 || c >= 5);
    }


    static void print(String title){
        if(!DEBUG) return;
        StringBuilder sb = new StringBuilder();
        sb.append("== " + title + " == turn : "+ K + "\n");
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                sb.append(map[i][j] + " ");
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }


}