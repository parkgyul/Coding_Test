import java.io.*;
import java.util.*;

class Solution
{
    static int N;
    static Node house, company;
    static List<Node> cus;
    static boolean[] visited;

    static int[][] dist;     
    static int[] startDist;  
    static int[] endDist;    

    static int min;

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for(int test_case = 1; test_case <= T; test_case++)
        {
            sb.append("#").append(test_case).append(" ");

            N = Integer.parseInt(br.readLine());
            visited = new boolean[N];
            cus = new ArrayList<>();

            st = new StringTokenizer(br.readLine());

            company = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            house = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            for(int i = 0; i < N; i++){
                cus.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

            dist = new int[N][N];
            startDist = new int[N];
            endDist = new int[N];

            for(int i = 0; i < N; i++){
                startDist[i] = calculate(company, cus.get(i));
                endDist[i] = calculate(cus.get(i), house);

                for(int j = 0; j < N; j++){
                    dist[i][j] = calculate(cus.get(i), cus.get(j));
                }
            }

            min = Integer.MAX_VALUE;

            dfs(0, -1, 0);

            sb.append(min).append("\n");
        }

        System.out.print(sb);
    }

    public static void dfs(int depth, int prev, int sum){
        if(sum >= min) return;

        // 모든 고객 방문
        if(depth == N){
            sum += (prev == -1 ? 0 : endDist[prev]);
            min = Math.min(min, sum);
            return;
        }

        for(int i = 0; i < N; i++){
            if(visited[i]) continue;

            visited[i] = true;

            if(prev == -1){
                dfs(depth+1, i, sum + startDist[i]);
            } else {
                dfs(depth+1, i, sum + dist[prev][i]);
            }

            visited[i] = false;
        }
    }

    public static int calculate(Node a, Node b){
        return Math.abs(a.i - b.i) + Math.abs(a.j - b.j);
    }

    static class Node{
        int i, j;
        Node(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
}