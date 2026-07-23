class Solution {
    static int[][] dun;
    static boolean[] visited;
    static int answer;
    
    public int solution(int k, int[][] dungeons) {
        answer = 0;
        dun = dungeons;
        visited = new boolean[dungeons.length];
        
        dfs(k, 0);
        return answer;
    }
    
    static void dfs(int rest, int depth){
        answer = Math.max(depth, answer);
        
        for(int i = 0; i < dun.length; i++){
            if(visited[i]) continue;
            if(rest < dun[i][0]) continue;
            
            visited[i] = true;
            dfs(rest-dun[i][1], depth+1);
            visited[i] = false;
        }
    }
}