import java.io.*;
import java.util.*;

public class Main {
    static List<Integer>[] nodes;
    static boolean[] visited;
    static int answer = 0;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        nodes = new ArrayList[N+1];
        for(int i =1; i <= N; i++){
            nodes[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            nodes[n1].add(n2);
            nodes[n2].add(n1);
        }

        visited = new boolean[N+1];
        visited[1] = true;
        dfs(1, 0);
        System.out.print(answer);
    }

    static void dfs(int prev, int cnt){
        for(int next : nodes[prev]){
            if(visited[next]) continue;
            visited[next] = true;
            answer++;
            dfs(next, cnt+1);
        }
    }
}