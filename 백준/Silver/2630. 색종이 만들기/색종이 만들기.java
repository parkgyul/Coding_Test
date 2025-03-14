import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class Main{
    static int white = 0, blue = 0;
    static int[][] arr;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        arr = new int[n][n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        cutPaper(0,0, n);
        System.out.println(white);
        System.out.println(blue);

    }
    static void cutPaper(int row, int col, int size){
        if(checkColor(row, col, size)){
            if(arr[row][col] == 0)
                white++;
            else
                blue++;

            return;
        }
        int cutSize = size/2;

        cutPaper(row, col, cutSize);
        cutPaper(row+cutSize, col, cutSize);
        cutPaper(row, col+cutSize, cutSize);
        cutPaper(row+cutSize, col+cutSize, cutSize);

    }

    static boolean checkColor(int row, int col, int size){
        int color = arr[row][col];
        for(int i = row; i< row+size; i++){
            for(int j = col; j < col+size; j++){
                if(arr[i][j] != color){
                    return false;
                }
            }
        }
        return true;
    }
}