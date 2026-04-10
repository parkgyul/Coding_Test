import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

public class Main{
    static Map<Integer, Set<Integer>> map;
    static int N;
    static int[][] arr;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1 , 1, 0};
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        map = new HashMap<>();
        arr = new int[N][N];

        for(int i = 0; i < N * N; i++){
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            Set<Integer> like = new HashSet<>();

            for(int j = 0; j < 4; j++){
                like.add(Integer.parseInt(st.nextToken()));
            }

            map.put(num, like);

            if(i == 0){
                arr[1][1] = num;
                continue;
            }

            Point p = find(num);
            arr[p.i][p.j] = num;
        }

        System.out.print(calculate());
    }

    public static int calculate(){
        int sum = 0;

        for(int i =0 ; i < N; i++){
            for(int j = 0; j < N; j++){
                int num = arr[i][j];
                int like = 0;
                Set<Integer> likes = map.get(num);

                for(int a = 0; a < 4; a++) {
                    int ni = i + dx[a];
                    int nj = j + dy[a];

                    if (ni < 0 || ni >= N || nj < 0 || nj >= N) continue;

                    if(likes.contains(arr[ni][nj])) like++;
                }

                if(like == 1) sum ++;
                else if(like == 2) sum += 10;
                else if(like == 3) sum += 100;
                else if(like == 4) sum += 1000;
            }
        }

        return sum;
    }

    public static Point find(int num){
        Set<Integer> likes = map.get(num);

        int bestLike = Integer.MIN_VALUE;
        int bestEmpty = Integer.MIN_VALUE;
        int pi = 0;
        int pj = 0;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++) {
                if(arr[i][j] > 0){
                    continue;
                }

                int empty = 0;
                int like = 0;

                for(int a = 0; a < 4; a++) {
                    int ni = i + dx[a];
                    int nj = j + dy[a];

                    if (ni < 0 || ni >= N || nj < 0 || nj >= N) continue;

                    if(arr[ni][nj] == 0) empty ++;

                    if(likes.contains(arr[ni][nj])) like++;
                }

                if(like > bestLike) {
                    pi = i; pj = j;
                    bestLike = like;
                    bestEmpty = empty;
                }else if(like == bestLike && empty > bestEmpty) {
                    pi = i; pj = j;
                    bestEmpty = empty;
                }
            }
        }

        return new Point(pi, pj);
    }

    public static class Point{
        int i, j;

        public Point(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
}