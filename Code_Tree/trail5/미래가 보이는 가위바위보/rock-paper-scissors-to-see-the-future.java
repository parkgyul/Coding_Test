import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        char[] arr = new char[N]; 
        for(int i = 0; i < N; i++){
            char ch = br.readLine().charAt(0);
            arr[i] = ch;

            if(ch == 'H'){
                arr[i] = 0;
            }else if(ch == 'P'){
                arr[i] = 1;
            }else{
                arr[i] = 2;
            }
        }

        // 주먹, 보, 가위
        int[] L, R;

        int max = 0;

        for(int a = 0; a < 3; a++){
            for(int b = 0; b < 3; b++){
                if(a == b) continue;

                L = new int[N];
                R = new int[N];
                
                L[0] = (arr[0] + 1)%3 == a ? 1: 0;
                R[N-1] = (arr[N-1] + 1)%3 == b ? 1 : 0;

                for(int i = 1; i < N; i++){
                    L[i] = L[i-1] + (((arr[i]+1)%3 == a) ? 1: 0);
                }

                for(int i = N-2; i >= 0; i--){
                    R[i] = R[i+1] + (((arr[i]+1)%3 == b) ? 1: 0) ;
                }

                for(int i = 0; i < N-1; i++){
                    max = Math.max(max, L[i] + R[i+1]);
                }

                max = Math.max(max, L[N-1]);
            }
        }

        System.out.print(max);


    }
}