import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main{
    static int[] parent;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        StringTokenizer st;

       parent = new int[N+1];
        for(int i = 1; i <= N; i++) parent[i] = i;

       for(int i = 1; i < N+1; i++){
           st = new StringTokenizer(br.readLine());
           for(int j = 1; j < N+1; j++){
               int val = Integer.parseInt(st.nextToken());
               if(val == 1){
                   union(i, j);
               }
           }
       }

       st = new StringTokenizer(br.readLine());
       int start = Integer.parseInt(st.nextToken());

       for(int i = 1; i < M; i++){
           int next = Integer.parseInt(st.nextToken());

           if(find(start) != find(next)) {
               System.out.print("NO");
               return;
           }
       }

       System.out.print("YES");
    }

    public static int find(int a){
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    public static void union(int i, int j){
        int pi = find(i);
        int pj = find(j);
        if(pi != pj) parent[pj] = pi;
    }
}