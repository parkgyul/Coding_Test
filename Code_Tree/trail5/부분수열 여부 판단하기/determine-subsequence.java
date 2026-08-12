import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arrN = new int[N];
        int[] arrM = new int[M];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arrN[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            arrM[i] = Integer.parseInt(st.nextToken());
        }
        
        int i = 0;

        for(int j = 0; j < M; j++){
            while(i < N && arrN[i] != arrM[j]){
                i++;
            }

            if(i == N){
                System.out.print("No");
                return;
            }else{
                i++;
            }
        }

        System.out.print("Yes");
    }
}