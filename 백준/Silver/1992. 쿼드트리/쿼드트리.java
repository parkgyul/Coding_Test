import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

public class Main{
    static int[][] arr;
    static StringBuilder sb;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0 ; j < n; j++){
                arr[i][j] = str.charAt(j) - '0';
            }
        }
        sb = new StringBuilder();

        cutPaper(0,0,n);
        System.out.println(sb);

    }
    public static void cutPaper(int row, int col, int size){
        if(isSameColor(row, col, size)){
//            sb.append("(");
            sb.append(arr[row][col]);

            return;
        }

        size /= 2;

        sb.append("(");
        cutPaper(row, col, size);

        cutPaper(row, col+size, size);

        cutPaper(row+size, col, size);

        cutPaper(row+size, col+size, size);
        sb.append(")");

    }
    public static boolean isSameColor(int row, int col, int size){
        int color = arr[row][col];
        for(int i = row; i < row+size; i++){
            for(int j = col; j < col+size; j++){
                if(arr[i][j] != color){
                    return false;
                }
            }
        }
        return true;
    }
}