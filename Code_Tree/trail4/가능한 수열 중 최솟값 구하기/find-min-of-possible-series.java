import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static boolean flag;
    static StringBuilder sb;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        sb = new StringBuilder();
        dfs(0);

        System.out.println(sb);
    }

    public static void dfs(int depth){
        if(flag) return;

        if(depth >= 4){
            int depthIndex = 2;
            for(int i = 0; i < depth/2-1; i++){
                int cnt = 0;
                for(int j = 0; j < depthIndex; j++){
                    if(arr[depth-1-j] == arr[depth-1-depthIndex-j]){
                        cnt++;
                    }
                }
                if(cnt == depthIndex){
                    return;
                }
                depthIndex++;
            }
        }

        if(depth == N){
            for(int i = 0 ; i < N; i++){
                sb.append(arr[i]);
            }
            flag = true;
            return;
        }

        for(int i = 4; i <= 6; i++){
            if(depth >=1 && arr[depth-1] == i) continue;
            arr[depth] = i;
            dfs(depth+1);
        }
    }
}