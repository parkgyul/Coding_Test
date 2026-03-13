import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
class Solution
{
    static int end, start;
    static int[] dx = {0,  0 };
    static int[] dy = {-1, 1};
    static boolean[][] visited;
    static int[][] arr;
  
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
		StringBuilder sb = new StringBuilder();
       
		for(int test_case = 1; test_case <= 10; test_case++)
		{
            br.readLine();
            sb.append("#").append(test_case).append(" ");
           
            arr = new int[100][100];
            for(int i = 0; i <100; i++){ // 입력 받기
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j<100; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if(arr[i][j] == 2) end = j;
                }
            }
            start  = -1;
            visited = new boolean[100][100];
            visited[99][end] = true;
            bfs();
            
            sb.append(start).append("\n");
		}
        
        System.out.print(sb);
	}
    public static void bfs(){
       Queue<Point> q = new LinkedList<>();
        q.add(new Point(99, end, 0));
        
        while(true){
            Point current = q.poll();
            if(current.i == 0) {
                start = current.j;
                return;
            }
            
            for(int i = current.dir; i < current.dir+2; i++){
                int next_i = current.i + dx[i%2];
                int next_j = current.j + dy[i%2];
                
                if( next_j < 0 || next_j >= 100) continue;
                if(visited[next_i][next_j] || arr[next_i][next_j] != 1) continue;
                
                visited[next_i][next_j] = true;
                q.add(new Point(next_i, next_j, i));
            }
             if(q.size() == 0){
                   visited[current.i -1][current.j] = true;
               	   q.add(new Point(current.i -1, current.j, 0));
             }
        }
    }
    
    public static class Point{
        int i, j, dir;
        public Point(int i, int j, int dir){
            this.i = i;
            this.j = j;
            this.dir = dir;
        }
    }
}