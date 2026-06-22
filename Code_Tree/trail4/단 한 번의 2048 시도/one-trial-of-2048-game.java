import java.io.*;
import java.util.*;

public class Main {
    static int[][] arr;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        arr = new int[4][4];

        for(int i =0 ; i < 4; i++){
             st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        String str = br.readLine();
        char ch = str.charAt(0);

        if(ch == 'R'){
            arr = pushRight();
        }else if(ch == 'L'){
            arr = pushLeft();
        }else if(ch == 'U'){
            arr = pushUp();
        }else{
            arr = pushDown();
        }

        for(int i =0 ; i < 4; i++){
            for(int j = 0; j < 4; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    static int[][] pushRight(){
        int[][] newArr = new int[4][4];

        for (int i = 0; i < 4; i++) {
            int index = 3;
            int temp = -1;  

            for (int j = 3; j >=0; j--) {  
                if (arr[i][j] == 0) continue;

                if (temp == arr[i][j]) {
                    newArr[i][index--] = temp * 2;
                    temp = -1; 
                } else {
                    if (temp != -1) {
                        newArr[i][index--] = temp;
                    }
                    temp = arr[i][j];
                }
            }

            if (temp != -1) {
                newArr[i][index] = temp;
            }
        }

        return newArr;
    }

   static int[][] pushLeft() {
    int[][] newArr = new int[4][4];

    for (int i = 0; i < 4; i++) {
        int index = 0;
        int temp = -1;  

        for (int j = 0; j < 4; j++) {  
            if (arr[i][j] == 0) continue;

            if (temp == arr[i][j]) {
                newArr[i][index++] = temp * 2;
                temp = -1;  // 합쳐졌으니 초기화
            } else {
                if (temp != -1) {
                    newArr[i][index++] = temp;
                }
                temp = arr[i][j];
            }
        }

        if (temp != -1) {
            newArr[i][index] = temp;
        }
    }

    return newArr;
}

    static int[][] pushDown(){
        int[][] newArr = new int[4][4];

        for (int j = 0; j < 4; j++) {
            int index = 3;
            int temp = -1;  

            for (int i = 3; i >= 0; i--) {  
                if (arr[i][j] == 0) continue;

                if (temp == arr[i][j]) {
                    newArr[index--][j] = temp * 2;
                    temp = -1;  
                } else {
                    if (temp != -1) {
                        newArr[index--][j] = temp;
                    }
                    temp = arr[i][j];
                }
            }

            if (temp != -1) {
                newArr[index][j] = temp;
            }
        }

        return newArr;
    }

    static int[][] pushUp(){
        int[][] newArr = new int[4][4];

        for (int j = 0; j < 4; j++) {
            int index = 0;
            int temp = -1;  

            for (int i = 0; i < 4; i++) {  
                if (arr[i][j] == 0) continue;

                if (temp == arr[i][j]) {
                    newArr[index++][j] = temp * 2;
                    temp = -1;  
                } else {
                    if (temp != -1) {
                        newArr[index++][j] = temp;
                    }
                    temp = arr[i][j];
                }
            }

            if (temp != -1) {
                newArr[index][j] = temp;
            }
        }

        return newArr;
    }
}