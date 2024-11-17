import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Queue;
public class Main {
    static StringBuilder sb = new StringBuilder();
    static int node,line,start;
    static int[][] arr;
    static boolean[] visited;
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //첫 줄 입력 받기(정점, 간선, 시작정점)
        node = Integer.parseInt(st.nextToken());
        line = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());

        //정점간의 관계를 저장할 배열
        arr = new int[node+1][node+1];
        //방문한 정점 기록용 배열
        visited = new boolean[node+1];

        //간선 입력 받기
        for(int i = 0; i<line; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a][b] = arr[b][a] = 1;
        }

        //dfs 구하기
        dfs(start);
        //출력값 개행 문자 추가 및 visited 배열 초기화
        sb.append("\n");
        Arrays.fill(visited, false);
        //bfs 구하기
        bfs(start);
        //출력
        System.out.println(sb);
    }
    public static void dfs(int start){
        //방문한 노드 기록하기
        visited[start] = true;
        //출력 stringbuilder에 append
        sb.append(start + " ");

        for(int i = 1; i<= node; i++) {
            if (arr[start][i] == 1 && !visited[i]) {
                dfs(i);
            }
        }
    }
    public static void bfs(int start){
        q.add(start);
        visited[start] = true;

        while(!q.isEmpty()){
            start = q.poll();
            sb.append(start+ " ");
            for(int i = 1; i<= node; i++){
                if(arr[start][i] == 1 && !visited[i]){
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
    }
}