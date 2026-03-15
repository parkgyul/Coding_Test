import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Solution
{
    static int N;
    static int[][] arr;
    static int index, result;
    static int[] dx = {0,0,-1, 1};
    static int[] dy = {-1,1, 0,0};
    public static void main(String args[]) throws Exception
    {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int test_case = 1; test_case <= T; test_case++)
        {
            sb.append("#").append(test_case).append(" ");

            N = Integer.parseInt(br.readLine());

            StringTokenizer st;
            arr = new int[N][N];
            for(int i = 0; i <N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            index = Integer.MAX_VALUE;
            result = Integer.MIN_VALUE;

            for(int i = 0; i <N; i++){
                for(int j = 0; j<N; j++){
                    bfs(i,j);
                }
            }

            sb.append(index).append(" ").append(result).append("\n");
        }

        System.out.print(sb);
    }
    public static void bfs(int i, int j){

        Queue<Node> q = new LinkedList<>();

        q.add(new Node(i, j, 1));

        while(!q.isEmpty()){
            Node cur = q.poll();

            for(int a = 0; a < 4; a++){
                int next_i = cur.i + dx[a];
                int next_j = cur.j + dy[a];

                if(next_i < 0 || next_i >= N || next_j < 0 || next_j >= N) continue;

                if(arr[next_i][next_j] == arr[cur.i][cur.j] +1){
                    q.add(new Node(next_i, next_j, cur.cnt+1));
                }

                if(result == cur.cnt){
                    index = Math.min(index, arr[i][j]);
                }else if (result < cur.cnt){
                    index = arr[i][j];
                    result = cur.cnt;
                }
            }

        }
    }

    public static class Node{
        int i, j, cnt;
        public Node( int i, int j, int cnt){
            this.i = i;
            this.j = j;
            this.cnt = cnt;
        }
    }
}