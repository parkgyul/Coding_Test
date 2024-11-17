import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    static int n, m;

    static int[][] map;
    static boolean[] visited;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n+1][n+1];
        visited = new boolean[n+1];

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = map[b][a] = 1;
        }
        bfs();
    }

    public static void bfs(){
        Queue<Integer> q = new LinkedList<>();
        int cnt = 0;
        for(int i = 1; i<n+1; i++){
            if(!visited[i]){
                q.add(i);
                while(!q.isEmpty()){
                    int current = q.poll();
                    for(int j = 1; j< n+1; j++){
                        if(map[current][j] == 1 && !visited[j]){
                            q.add(j);
                            visited[j] = true;
                        }
                    }
                }
                cnt ++;
            }
            else continue;
        }
        System.out.println(cnt);
    }
}