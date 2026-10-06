import java.io.*;
import java.util.*;

public class Main {
    static boolean DEBUG = true;
    static int N, M;
    static int[][] map;
    static Map<Integer, int[]> packages;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
    
        packages = new HashMap<>();

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int r = putPackage(k, h, h, w, c);

            // print("패키지 놓음 :" + k);

            packages.put(k, new int[]{r-h+1, c, h, w});
        }

        boolean isLeft = true;
        StringBuilder result = new StringBuilder();
        while(M-- > 0){
            int picked = pickOne(isLeft);
            remove(picked);
            packages.remove(picked);
            pull();
            result.append(picked).append("\n");
            isLeft = !isLeft;
        }

        System.out.print(result);
    }
    static void pull(){
        List<Integer> ids = new ArrayList<>(packages.keySet());
        // 아래쪽 행(top + h - 1)이 큰 순서로
        ids.sort((a, b) -> {
            int[] A = packages.get(a), B = packages.get(b);
            return (B[0] + B[2]) - (A[0] + A[2]);
        });

        for(int id : ids){
            int[] info = packages.get(id);
            remove(id);
            int newR = putPackage(id, info[0] + info[2] - 1, info[2], info[3], info[1]);
            packages.put(id, new int[]{newR - info[2] + 1, info[1], info[2], info[3]});
        }
    }

    static void remove(int picked){
        int[] info = packages.get(picked);
        int r = info[0];
        int c = info[1];
        int h = info[2];
        int w = info[3];

        for(int i = r; i <= r+h-1; i++){
            for(int j = c; j <= c+w-1; j++){
                map[i][j] = 0;
            }
        }
    }

    static int pickOne(boolean isLeft){
        int picked = -1;
        Map<Integer, Integer> walls = new HashMap<>();

        for(int i = 1; i <= N; i++){
            if(isLeft){
                for(int j = 1; j <= N; j++){
                    if(map[i][j] != 0){
                        walls.put(map[i][j], walls.getOrDefault(map[i][j], 0) + 1);
                        break;
                    }
                }
            }else{
                for(int j = N; j >= 1; j--){
                    if(map[i][j] != 0){
                        walls.put(map[i][j], walls.getOrDefault(map[i][j], 0) + 1);
                        break;
                    }
                }
            }
        }
        int min = 102;

        for(int key : walls.keySet()){
            int num = walls.get(key);
            if(min > key && num == packages.get(key)[2]){
                min = key;
            }
        }

        return min;
    }

    static int putPackage(int k, int initH, int h, int w, int c){
        int r = initH;

        while(canPlace(r+1, c, w)){
            r++;
        }

        for(int i = r; i >= r-h+1; i--){
            for(int j = c; j <= c+w-1; j++){
                map[i][j] = k;
            }
        }

        return r;
    }

    static boolean canPlace(int r, int c, int w){
        if(isOutside(r, c)) return false;
        for(int j = c; j <= c+w-1; j++){
            if(map[r][j] != 0) return false;
        }

        return true;
    }

    static boolean isOutside(int r, int c){
        return (r <= 0 || r > N || c <= 0 || c > N);
    }

    static void print(String title){
        if(!DEBUG) return;
        StringBuilder sb = new StringBuilder();

        sb.append("==" + title + "==\n");
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}