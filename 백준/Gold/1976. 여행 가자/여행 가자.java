import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main{
    static int[] parent;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        StringTokenizer st;

        parent = new int[N+1];
        for(int i = 1; i <= N; i++) parent[i] = i;

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                int val = Integer.parseInt(st.nextToken());

                if(val == 1){
                    union(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int first = Integer.parseInt(st.nextToken());

        for(int i = 1; i < M; i++){
            int next = Integer.parseInt(st.nextToken());

            if(find(first) != find(next)){
                System.out.print("NO");
                return;
            }
        }

        System.out.print("YES");

    }
    static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b){
        int pa = find(a);
        int pb = find(b);

        if(pa != pb) parent[pb] = pa;
    }
}