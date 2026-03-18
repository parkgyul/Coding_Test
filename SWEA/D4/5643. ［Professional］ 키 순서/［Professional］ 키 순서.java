import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
class Solution
{
    static int N, M;
    static int total;
    static int count;
    static List<Integer>[] smaller;
    static List<Integer>[] bigger;
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
            M = Integer.parseInt(br.readLine());

            smaller = new ArrayList[N+1];
            bigger = new ArrayList[N+1];
            
            for(int i = 1; i <= N; i++){
                smaller[i] = new ArrayList<>();
                bigger[i] = new ArrayList<>();
            }
            
            for(int i = 0 ; i < M ; i++){
                st = new StringTokenizer(br.readLine()); 
                int small = Integer.parseInt(st.nextToken());
                int big = Integer.parseInt(st.nextToken());
                
                smaller[big].add(small);
                bigger[small].add(big);
            }
            
            total = 0;
            for(int i = 1; i <= N; i++){
                count = 1; 
                find(i);
                if(count == N) total++;
            }
                
            
		
            sb.append(total).append("\n");
        }
        
        System.out.print(sb);
	}
    
    public static void find(int start){
        boolean[] visited = new boolean[N+1];
        visited[start] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        // 키 작은 사람 찾기
          while(!q.isEmpty()){
            int cur = q.poll();
            
            for(int next : smaller[cur]){
                if(!visited[next]){
                    q.add(next);
                    visited[next] = true;
                    count++;
                }
            }
          }
        
        q.add(start);

        // 키 큰 사람 찾기
        while(!q.isEmpty()){
            int cur = q.poll();
            
            for(int next : bigger[cur]){
                if(!visited[next]){
                    visited[next] = true;
                    q.add(next);
                    count++;
                }
            } 
        }
    }
}