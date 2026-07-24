class Solution {
    static final int OFFSET = 1000000007;
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        
        boolean[][] puddleArr = new boolean[n+1][m+1];
        for(int[] p : puddles){
            puddleArr[p[1]][p[0]] = true;
        }
        
        int[][] dp = new int[n+1][m+1];
        dp[1][1] = 1;
        
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(i== 1 &&j == 1) continue;
                if(puddleArr[i][j]) continue;
                
                dp[i][j] =((puddleArr[i-1][j] ? 0 :  dp[i-1][j]%OFFSET) + (puddleArr[i][j-1] ? 0 : dp[i][j-1] % OFFSET)) % OFFSET; 
            }
        }
        
        return dp[n][m];
    }
}