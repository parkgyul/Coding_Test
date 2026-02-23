import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedReader;

import java.util.Arrays;

class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int sum = 0;

        char[] arr = str.toCharArray();

        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();

        for(int i = arr.length-1; i >= 0; i--){
            int num = arr[i] - '0';
            sum += num;
            sb.append(num);
        }

        if(arr[0] != '0' || sum % 3 != 0){
            System.out.print(-1);
            return;
        }

        System.out.print(sb);
    }
}