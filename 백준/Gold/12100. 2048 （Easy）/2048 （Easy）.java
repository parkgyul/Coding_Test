import java.io.IOException;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class Main{
    static int N;
    static int result = Integer.MIN_VALUE;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[][] board = new int[N][N];

        StringTokenizer st;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        playGame(0, board);
        System.out.print(result);
    }

    private static void playGame(int depth,int[][] board){
        if(depth == 5){
            result = Math.max(findMax(board), result);
            return;
        }

        playGame(depth+1, pushLeft(board));
        playGame(depth+1, pushRight(board));
        playGame(depth+1, pushUp(board));
        playGame(depth+1, pushDown(board));
    }

    private static int[][] pushLeft(int[][] board){
        int[][] newBoard = new int[N][N];
        int idx;
        for(int i = 0; i < N; i++){
            idx = 0;
            for(int j = 0; j < N; j++){
                if(board[i][j] == 0) continue;

                if(newBoard[i][idx] == 0){ //아직 값이 없을 때
                    newBoard[i][idx] = board[i][j];
                }else if(newBoard[i][idx] == board[i][j]){
                    newBoard[i][idx++] *= 2;
                }else{
                    newBoard[i][++idx] = board[i][j];
                }
            }
        }

        return newBoard;
    }

    private static int[][] pushRight(int[][] board){
        int[][] newBoard = new int[N][N];
        int idx;

        for(int i = 0; i < N; i++){
            idx = N-1;
            for(int j = N-1; j >= 0; j--){
                if(board[i][j] == 0) continue;

                if(newBoard[i][idx] == 0){
                    newBoard[i][idx] = board[i][j];
                }else if(newBoard[i][idx] == board[i][j]){
                    newBoard[i][idx--] *= 2;
                }else{
                    newBoard[i][--idx] = board[i][j];
                }
            }
        }

        return newBoard;
    }

    private static int[][] pushUp(int[][] board){
        int[][] newBoard = new int[N][N];
        int idx;

        for(int j = 0; j < N; j++){
            idx = 0;
            for(int i = 0; i < N; i++){
                if(board[i][j] == 0) continue;

                if(newBoard[idx][j] == 0){
                    newBoard[idx][j] = board[i][j];
                }else if(newBoard[idx][j] == board[i][j]){
                    newBoard[idx++][j] *= 2;
                }else{
                    newBoard[++idx][j] = board[i][j];
                }
            }
        }

        return newBoard;
    }

    private static int[][] pushDown(int[][] board){
        int[][] newBoard = new int[N][N];
        int idx;

        for(int j = 0; j < N; j++){
            idx = N-1;
            for(int i = N-1; i >= 0; i--){
                if(board[i][j] == 0) continue;

                if(newBoard[idx][j] == 0){
                    newBoard[idx][j] = board[i][j];
                }else if(newBoard[idx][j] == board[i][j]){
                    newBoard[idx--][j] *= 2;
                }else{
                    newBoard[--idx][j] = board[i][j];
                }
            }
        }

        return newBoard;
    }

    private static int findMax(int[][] board){
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                max = Math.max(max, board[i][j]);
            }
        }

        return max;
    }
}