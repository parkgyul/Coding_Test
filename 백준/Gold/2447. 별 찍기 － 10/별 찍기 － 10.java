import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
    static String[][] arr;
    static StringBuilder sb;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        arr = new String[n][n];

        star(0,0,n);
        
        sb = new StringBuilder();
        
        for(int i = 0; i<n; i++){
            for(int j = 0 ; j<n; j++){
                if(arr[i][j] == null) sb.append(" ");
                else sb.append(arr[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
    public static void star(int x, int y, int size){
        if(size == 1){
            arr[x][y] = "*";
            return;
        }

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(!(i == 1 && j == 1)){
                    star(x+i*(size/3), y+ j*(size/3), size/3);
                }
            }
        }
    }
}