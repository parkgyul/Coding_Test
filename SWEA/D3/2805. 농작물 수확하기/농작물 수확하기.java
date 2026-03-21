import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;

class Solution
{
    static int N;
    static int[][] arr;
    static boolean[][] visited;
    static int total;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++)
		{
            sb.append("#").append(test_case).append(" ");
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            String str;
            for(int i = 0; i < N; i++){
                str = br.readLine();
                for(int j = 0; j < N; j++){
                    arr[i][j] = str.charAt(j)- '0';
                }
            }
            visited = new boolean[N][N];
            total = 0;
            bfs();
            
            sb.append(total).append("\n");
		}
        System.out.print(sb);
	}
    
    public static void bfs(){
        Queue<Node> q = new LinkedList<>();
        int max = N/2;
        visited[max][max] = true;
        q.add(new Node(max, max, 0));
        
        while(!q.isEmpty()){
            Node cur = q.poll();
            int cur_i = cur.i;
            int cur_j = cur.j;
            total += arr[cur_i][cur_j];
            
            int cur_cnt = cur.cnt;
            if(cur_cnt+1 > max)
                continue;
            for(int i = 0; i < 4; i ++){
                int next_i = cur_i + dx[i];
                int next_j = cur_j + dy[i];
                
                if(visited[next_i][next_j])
                    continue;
                
                q.add(new Node(next_i, next_j, cur_cnt+1));
                visited[next_i][next_j] = true;
            }
        }
    }
    
    public static class Node{
        int i, j, cnt;
        public Node(int i, int j, int cnt){
            this.i = i;
            this.j = j;
            this.cnt = cnt;
        }
    }
}