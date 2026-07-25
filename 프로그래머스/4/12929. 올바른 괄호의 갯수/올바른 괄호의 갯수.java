class Solution {
    static int N;
    static int answer;
    public int solution(int n) {
        answer = 0;
        N = n;
        dfs(0, 0, 0);
        return answer;
    }
    
    static void dfs(int open, int close, int depth){
        if(open + close == 2*N){
            answer++;
            return;
        }
        
        if(open+1 <= N){
            dfs(open+1, close, depth+1);
        }
        if(open >= close+1){
            dfs(open, close+1, depth+1);
        }
    }
}