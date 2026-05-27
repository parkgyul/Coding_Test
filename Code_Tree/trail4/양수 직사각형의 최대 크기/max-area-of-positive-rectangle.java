import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = -1;

        for(int i =0 ; i < N; i++){
            for(int j = 0 ; j < M; j++){
                for(int k = i ; k < N; k++){
                    for(int l = j; l < M; l++){
                        if(isPositive(i, j, k, l)){
                            answer = Math.max(answer, (k-i+1)*(l-j+1));
                        }
                    }
                }
            }
        }

        System.out.println(answer);
    }

    static boolean isPositive(int x1, int y1, int x2, int y2){
        for(int i = x1; i <= x2; i++){
            for(int j = y1; j <= y2; j++){
                if(arr[i][j] <= 0) return false;
            }
        }

        return true;
    }
}