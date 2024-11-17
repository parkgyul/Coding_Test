import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    static int[][] my = new int[5][5];
    static boolean up = true , down = true;
    static int total;
    public static void main(String[] arg)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;



        for(int i = 0; i<5; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<5; j++){
                my[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        total = 0;
        int cnt =0;
        for(int i = 0; i<5; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j<5; j++){
                int num = Integer.parseInt(st.nextToken());
                cnt ++;
                for(int m = 0; m<5; m++){
                    for(int n = 0; n<5; n++){
                        if(my[m][n] == num) {
                            my[m][n] = 0;
                            total += bingoCheck(m, n);
                            if(total >= 3 ) {System.out.println(cnt); return;}
                            break;
                        }
                    }
                }
            }
        }
    }
    public static int bingoCheck(int i, int j){

        int sum =0;

        boolean row = true, column = true, diagonal1= false, diagonal2=false;

        // 가로
        for(int m = 0; m<5; m++ ){
            if(my[i][m] != 0 ) row = false;
        }

        //세로
        for(int m = 0; m<5; m++){
            if(my[m][j] != 0 ) column = false;
        }

        //대각선 오른쪽으로
        if(up) {
            diagonal1 = true;
            for (int m = 0; m < 5; m++) {
                if (my[m][m] != 0) diagonal1 = false;
            }
            if(diagonal1) up = false;
        }

        //대각선 왼쪽으로
        if(down) {
            diagonal2 = true;
            for(int m = 0; m<5; m++){
                if(my[4-m][m] != 0 ) diagonal2 = false;
            }
            if(diagonal2) down = false;
        }

        if(row) {sum++;}
        if(column) { sum++;}
        if(diagonal1) {sum++;}
        if(diagonal2) {sum++;}

        return sum;
    }

}