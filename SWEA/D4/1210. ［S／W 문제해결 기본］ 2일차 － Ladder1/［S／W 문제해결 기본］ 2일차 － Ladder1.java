import java.io.*;
import java.util.*;

class Solution {

    static int[][] arr = new int[100][100];

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for(int t = 1; t <= 10; t++){

            br.readLine();
            sb.append("#").append(t).append(" ");

            int x = 0;

            for(int i=0;i<100;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<100;j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());

                    if(arr[i][j] == 2)
                        x = j;
                }
            }

            int y = 99;

            while(y > 0){

                if(x > 0 && arr[y][x-1] == 1){
                    while(x > 0 && arr[y][x-1] == 1)
                        x--;
                }

                else if(x < 99 && arr[y][x+1] == 1){
                    while(x < 99 && arr[y][x+1] == 1)
                        x++;
                }

                y--;
            }

            sb.append(x).append("\n");
        }

        System.out.print(sb);
    }
}