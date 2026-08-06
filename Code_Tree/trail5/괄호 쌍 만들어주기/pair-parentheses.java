import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] arr = br.readLine().toCharArray();
        int N = arr.length;
        int[] R = new int[N];

        R[N-1] = 0;

        for(int i = N-2; i >= 0; i--){
            if(arr[i] == ')' && arr[i+1] == ')'){
                R[i] = R[i+1] + 1;
            }else{
                R[i] = R[i+1];
            }
        }

        long sum = 0;
        for(int i = 1; i <= N-3; i++){
            if(arr[i-1] == '(' && arr[i] == '('){
                sum += R[i];
            }
        }

        System.out.print(sum);
    }
}