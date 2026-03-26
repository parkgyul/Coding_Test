import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

class Solution {
    static List<Integer>[] friends;
    static boolean[] checked;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int test_case = 1; test_case <= T; test_case++){
            sb.append("#").append(test_case).append(" ");
            
            st = new StringTokenizer(br.readLine());
            
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            
            friends = new ArrayList[N+1];
            for(int i = 1; i <= N; i++){
                friends[i] = new ArrayList<>();
            }
            
            for(int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                
                friends[from].add(to);
                friends[to].add(from);
            }
            
            checked = new boolean[N+1];
            
            int cnt = 0;
            
            for(int i = 1; i < N+1; i++){
                if(!checked[i]){
                    bfs(i);
                    checked[i] = true;
                    cnt++;
                }
            }
            
            sb.append(cnt).append("\n");
        }
        
        System.out.print(sb);
    }
    
    public static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        
        while(!q.isEmpty()){
            int cur = q.poll();
            
            for(int next: friends[cur]){
                if(checked[next]) continue;
                
                q.add(next);
                checked[next] = true;
            }
            
            
        }
        
    }
}