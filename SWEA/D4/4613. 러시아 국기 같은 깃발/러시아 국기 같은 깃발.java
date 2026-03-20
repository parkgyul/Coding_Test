import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

class Solution
{
    static int N, M;
    static int[][] arr;
    static int total;
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
            
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            
            arr = new int[N][3];
            for(int i = 0; i < N; i ++){
                String str = br.readLine();
                for(int j = 0; j < M; j++){
                    char ch = str.charAt(j);
                    if( ch == 'W') arr[i][0] ++;
                    else if( ch == 'B') arr[i][1] ++;
                    else arr[i][2] ++;
                }
            }
            total = (M - arr[0][0]);
            
            min = Integer.MAX_VALUE;
            
            dfs(0, total, 0);
            
            sb.append(min).append("\n");
		}
        System.out.print(sb);
	}
    public static void dfs(int depth, int total, int selectedColor){
        if(depth == N-1){
            if(selectedColor != 2) return;
            min = Math.min(min, total);
            return;
        }
        
        if(depth == N-2 && selectedColor == 0)
            return;

        // 같은 색 유지
        dfs(depth+1, total + M - arr[depth+1][selectedColor], selectedColor);

        if(selectedColor < 2){
            dfs(depth+1, total + M - arr[depth+1][selectedColor + 1], selectedColor + 1);
        }
    }
}