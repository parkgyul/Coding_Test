import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader; 
import java.util.Map;
import java.util.HashMap;
class Solution
{
    static int N;
    static Map<Integer, Integer> starts;
    static Map<Integer, Integer> ends;
    static boolean[] linked;
    static int start_point;
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++)
		{
            sb.append("#").append(test_case).append(" ");
            N = Integer.parseInt(br.readLine());
            starts = new HashMap<>();
            ends = new HashMap<>();
            st = new StringTokenizer(br.readLine());
            int s = 0, e = 0;
            for(int i =0 ; i < N; i++){
                s =  Integer.parseInt(st.nextToken());
                e = Integer.parseInt(st.nextToken());
                starts.put(s,e);
                ends.put(e, s);
            }
            linked = new boolean[100];
            
            linked[s] = true;
            dfs(1, s, starts.get(s));
            
            for(int i = 0; i < N; i++){
                sb.append(start_point).append(" ").append(starts.get(start_point)).append(" ");
                start_point = starts.get(start_point);
            }
            
            sb.append("\n");
		}
        System.out.print(sb);
	}
    public static void dfs(int depth, int start, int end){
        if(depth == N){
            start_point = start;
            return;
        }
        
        Integer new_end = starts.get(end);
        if(new_end != null && !linked[end]){
            linked[end] = true;
            dfs(depth+1, start, new_end);
            return;
        }
        
        Integer new_start = ends.get(start);
        if(new_start != null && !linked[new_start]){
            linked[new_start] = true;
            dfs(depth+1, new_start, end);
            return;
        }
    }
    
    public static class Node{
        int left, right;
        public Node(int left, int right){
            this.left = left;
            this.right = right;
        }
    }
}