import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.util.ArrayList;

public class Main{
    static int start, end ;
    static ArrayList<ArrayList<Integer>> graph;
    static int answer = -1;
    static boolean[] visited;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        graph = new ArrayList<>();

        int n = Integer.parseInt(br.readLine());

        visited = new boolean[n+1];

        for(int i = 0; i<= n; i++) graph.add(new ArrayList<>());

        StringTokenizer st = new StringTokenizer(br.readLine());

        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            graph.get(parent).add(child);
            graph.get(child).add(parent);
        }

        dfs(start, 0);

        System.out.println(answer);
    }
    public static void dfs(int point, int cnt){
        visited[point] = true;
        for(int i : graph.get(point)){
            if(!visited[i]) {
                if (i == end) {
                    answer = cnt + 1;
                    return;
                }
                dfs(i, cnt+1);
            }
        }
    }
}