import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Arrays;

class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        // String -> char Array
        char[] arr = str.toCharArray();
        
        Arrays.sort(arr);

        int sum = 0;

        // 마지막 결과를 출력할 StringBuilder
        StringBuilder sb = new StringBuilder();

        for(int i = arr.length-1; i >= 0; i--){
            sum += arr[i] - '0';
            sb.append(arr[i]);
        }

        // 조건 1. 끝자리가 0으로 끝나야함.
        // 조건 2. 모든 수의 합이 3의 배수가 되어야 함.
        if( arr[0] != '0' || sum % 3 != 0){
            System.out.print(-1);
            return;
        }

        System.out.print(sb);
    }
}