import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
class Solution
{
    static int V;
    static boolean[] visited;
    static List<Integer>[] parents;
    static List<Integer>[] childs;
    static int finding1 , finding2;
    static int max;
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
            sb.append("#").append(test_case).append(" ");
            st = new StringTokenizer(br.readLine());
            
            V  = Integer.parseInt(st.nextToken());
          	int E = Integer.parseInt(st.nextToken());
            finding1 = Integer.parseInt(st.nextToken());
            finding2 = Integer.parseInt(st.nextToken());
            visited = new boolean[V+1];
            
            parents = new ArrayList[V+1];
            childs = new ArrayList[V+1];
            for(int i = 1; i < V+1; i++){
                parents[i] = new ArrayList<>();
                childs[i] = new ArrayList<>();
            }
            
            st = new StringTokenizer(br.readLine());
            for(int i =0 ; i < E; i ++){
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());
                
                childs[parent].add(child);
                parents[child].add(parent);
            }
            max = -1;
            findMax();
            sb.append(max).append(" ").append(findChild()).append("\n");
		}
        System.out.print(sb);
	}
    
    public static void findMax(){
        Queue<Integer> q = new LinkedList<>();
        q.add(finding1);
        q.add(finding2);
        visited[finding1] = true;
        visited[finding2] = true;
        
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int next : parents[cur]){
                if(visited[next] == true){
                    max = Math.max(next, max);
                    return;
                }
                q.add(next);
                visited[next] = true;
            }
        }
    }
    
    public static int findChild(){
        Queue<Integer> q = new LinkedList<>();
        int cnt = 1;
        q.add(max);

         while(!q.isEmpty()){
            int cur = q.poll();
            for(int next : childs[cur]){ 
                q.add(next);
                cnt++;
            }
        }
        return cnt;
    }
}