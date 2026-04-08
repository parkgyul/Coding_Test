import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.io.InputStreamReader;

public class Main {
    static int N, M;
    static int[][] plus;
    static int[][] vit;
    static Deque<Integer>[][] trees;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        plus = new int[N+1][N+1];
        vit = new int[N+1][N+1];
        trees = new ArrayDeque[N+1][N+1];

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =1 ; j <= N; j++){
                plus[i][j] = Integer.parseInt(st.nextToken());
                trees[i][j] = new ArrayDeque<>();
                vit[i][j] = 5;
            }
        }

        for(int i =0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());

            trees[r][c].addLast(a);
        }



        while(K-- > 0){
            spring_summer();
            fall();
            winter();
        }

        int total = 0;
        for(int i = 1; i <= N; i++){
            for(int j = 1; j<= N; j++){
                total += trees[i][j].size();
            }
        }

        System.out.print(total);
    }

    private static void spring_summer(){
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(trees[i][j].isEmpty()) continue;
                Deque<Integer> newTrees = new ArrayDeque<>();
                int dead = 0;
                while(!trees[i][j].isEmpty()){
                    int age = trees[i][j].pollFirst();

                    if(vit[i][j] >= age){
                        vit[i][j] -= age;
                        newTrees.addLast(age+1);
                    }else{
                        dead += age/2;
                    }
                }
                vit[i][j] += dead;
                trees[i][j] = newTrees;
            }
        }

    }

    private static void fall(){

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(trees[i][j].isEmpty()) continue;

                for(int age : trees[i][j]){

                    if(age % 5 != 0) continue;

                    for(int a = 0; a < 8; a++){
                        int ni = i + dx[a];
                        int nj = j + dy[a];

                        if(ni < 1 || ni > N || nj < 1 || nj > N)
                            continue;

                        trees[ni][nj].addFirst(1);
                    }

                }
            }
        }


    }

    private static void winter(){
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++) {
                vit[i][j] += plus[i][j];
            }
        }

    }
}