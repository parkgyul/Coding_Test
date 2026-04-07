import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int N;
    static int[][] arr;
    static int result = Integer.MIN_VALUE;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N][N];

        for(int i =0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, arr);

        System.out.print(result);
    }

    public static void dfs(int depth, int[][] board){
        if(depth == 5){
            result = Math.max(findMax(board), result);
            return;
        }

        for(int i = 0; i < 4; i++){
            int[][] newBoard = move(board, i);
            dfs(depth+1, newBoard);
        }
    }

    public static int[][] move(int[][] board, int dir){
        int[][] next = copy(board);

        switch(dir){
            case 0: // 상
                for(int c = 0; c < N; c++){
                    int[] line = new int[N];
                    int idx = 0;

                    for(int r = 0; r < N; r++){
                        if(next[r][c] != 0){
                            line[idx++] = next[r][c];
                        }
                    }
                    int[] merged = merge(line);

                    for(int r = 0; r < N; r++){
                        next[r][c] = merged[r];
                    }
                }
                break;

            case 1: // 하
                for(int c = 0; c < N; c++){
                    int[] line = new int[N];
                    int idx = 0;

                    for(int r = N-1; r >= 0; r--){
                        if(next[r][c] != 0){
                            line[idx++] = next[r][c];
                        }
                    }

                    int[] merged = merge(line);

                    for(int r = N-1; r >= 0; r--){
                        next[r][c] = merged[N-r-1];
                    }
                }
                break;

            case 2: // 좌
                for(int r = 0; r < N; r++){
                    int[] line = new int[N];
                    int idx = 0;

                    for(int c = 0; c < N; c++){
                        if(next[r][c] != 0){
                            line[idx++] = next[r][c];
                        }
                    }

                    int[] merged = merge(line);

                    for(int c = 0; c < N; c++){
                        next[r][c] = merged[c];
                    }
                }
                break;
            case 3: // 우
                for(int r = 0; r < N; r++){
                    int[] line = new int[N];
                    int idx = 0;

                    for(int c = N-1; c>= 0; c--){
                        if(next[r][c] != 0){
                            line[idx++] = next[r][c];
                        }
                    }

                    int[] merged = merge(line);

                    for(int c = N-1; c>= 0; c--){
                        next[r][c] = merged[N-c-1];
                    }
                }
                break;
        }

        return next;
    }

    public static int[] merge(int[] line){
        int[] result = new int[N];
        int idx = 0;

        for(int i = 0; i < N; i++){
            if(line[i] == 0) continue;

            if(i+1 < N && line[i] == line[i+1]){
                result[idx++] = line[i] *2;
                i += 1;
            }else{
                result[idx++] = line[i];
            }
        }

        return result;
    }

    public static int findMax(int[][] arr){
        int max = Integer.MIN_VALUE;
        for(int i =0 ; i < N; i++){
            for(int j = 0; j < N; j++){
                max = Math.max(max, arr[i][j]);
            }
        }

        return max;
    }

    public static int[][] copy(int[][] board){
        int[][] newBoard = new int[N][N];

        for(int i = 0; i < N; i++){
            newBoard[i] = board[i].clone();
        }

        return newBoard;
    }
}