import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] arr;
    static int answer = 0;

    static int[] dx = {-1, -1, 1, 1};
    static int[] dy = {1, -1, -1, 1};

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];

        StringTokenizer st;

        for(int i =0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0 ; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                for(int len1 = 1; len1 < N; len1++){
                    for(int len2 = 1; len2 < N; len2++){
                        check(i, j, len1, len2);
                    }
                }
            }
        }

        System.out.print(answer);
    }

    static void check(int r, int c, int len1, int len2){
        int sum = 0;

        int cr = r;
        int cc = c;

        int[] lens = {len1, len2, len1, len2};

        for(int dir = 0; dir < 4; dir++){
            for(int step = 0; step < lens[dir]; step++){
                cr += dx[dir];
                cc += dy[dir];

                if(cr < 0 || cr >= N || cc < 0 || cc >= N) return;

                sum += arr[cr][cc];
            }
        }
        answer = Math.max(answer, sum);
    }
}