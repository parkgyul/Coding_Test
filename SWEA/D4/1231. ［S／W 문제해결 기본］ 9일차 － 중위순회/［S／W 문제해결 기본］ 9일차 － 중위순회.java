import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
class Solution
{
    static List<Integer>[] arr;
    static char[] chars;
    static boolean[] visited;
    static StringBuilder result;
    static Stack<Character> st;
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= 10; test_case++)
		{
            sb.append("#").append(test_case).append(" ");
            int N = Integer.parseInt(br.readLine());
            arr = new ArrayList[N+1];
            chars = new char[N+1];
            visited = new boolean[N+1];
            st = new Stack<>();
            for(int i = 1; i < N+1; i++){
                arr[i] = new ArrayList<>();
            }
            
            for(int i  = 0; i < N; i ++){
                stringTokenizer = new StringTokenizer(br.readLine());
                int num = Integer.parseInt(stringTokenizer.nextToken());
                char ch = stringTokenizer.nextToken().charAt(0);
                chars[num] = ch;
                
                if(stringTokenizer.hasMoreTokens()){
                    int left = Integer.parseInt(stringTokenizer.nextToken());
                    arr[num].add(left);
                }
                
                if(stringTokenizer.hasMoreTokens()){
                    int right = Integer.parseInt(stringTokenizer.nextToken());
                    arr[num].add(right);
                }  
            }
            result = new StringBuilder();
            dfs(1);
            
            sb.append(result).append("\n");
		}
        System.out.print(sb);
	}
    
    public static void dfs(int i){
            if(arr[i].size() >= 1){ 
                dfs(arr[i].get(0));
            }
            result.append(chars[i]);
        
            if(arr[i].size() == 2){
                dfs(arr[i].get(1));
            }
    }
}