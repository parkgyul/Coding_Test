import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;

import java.util.Set;
import java.util.HashSet;
class Solution
{
    static int[][] arr;
    static Set<String> s;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int test_case = 1; test_case <= T; test_case++)
        {
            sb.append("#").append(test_case).append(" ");

            s = new HashSet<>();

            arr = new int[4][4];
            for(int i = 0; i < 4; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < 4; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            for(int i = 0; i <4; i ++){
                for(int j = 0; j<4; j++){
                    String str = Integer.toString(arr[i][j]);
                    dfs(i,j,1, str);
                }
            }

            sb.append(s.size()).append("\n");
        }
        System.out.print(sb);
    }

    public static void dfs(int i, int j, int depth, String str){
        if(depth == 7){
            s.add(str);
            return;
        }

        for(int a = 0; a < 4; a++){
            int ni = i + dx[a];
            int nj = j + dy[a];

            if(ni < 0 || ni >= 4 || nj <0 || nj >= 4)
                continue;
            
            dfs(ni, nj, depth+1, str + Integer.toString(arr[ni][nj]));
        }
    }
}