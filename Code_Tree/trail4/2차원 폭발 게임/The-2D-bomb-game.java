import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // K번 반복
        for(int t = 0; t < K; t++) {

            // 1. 터질 때까지 반복
            while(true){
                boolean exploded = burst();
                if(!exploded) break;
                applyGravity();
            }

            // 2. 회전
            rotate();

            // 3. 중력
            applyGravity();
        }

        // 마지막 폭발 처리
        while(true){
            boolean exploded = burst();
            if(!exploded) break;
            applyGravity();
        }

        // 남은 폭탄 개수 세기
        int cnt = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(arr[i][j] != 0) cnt++;
            }
        }

        System.out.println(cnt);
    }

    // 폭발 처리 (연속 M 이상 제거)
    static boolean burst() {
        boolean exploded = false;

        for(int j = 0; j < N; j++){
            int i = 0;

            while(i < N){
                if(arr[i][j] == 0){
                    i++;
                    continue;
                }

                int value = arr[i][j];
                int start = i;
                int count = 1;
                i++;

                while(i < N && arr[i][j] == value){
                    count++;
                    i++;
                }

                if(count >= M){
                    exploded = true;
                    for(int k = start; k < start + count; k++){
                        arr[k][j] = 0;
                    }
                }
            }
        }

        return exploded;
    }

    // 전체 중력 적용
    static void applyGravity() {
        for(int j = 0; j < N; j++){
            int[] temp = new int[N];
            int idx = N - 1;

            for(int i = N - 1; i >= 0; i--){
                if(arr[i][j] != 0){
                    temp[idx--] = arr[i][j];
                }
            }

            for(int i = 0; i < N; i++){
                arr[i][j] = temp[i];
            }
        }
    }

    // 시계 방향 90도 회전
    static void rotate() {
        int[][] newArr = new int[N][N];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                newArr[j][N - 1 - i] = arr[i][j];
            }
        }

        arr = newArr;
    }
}