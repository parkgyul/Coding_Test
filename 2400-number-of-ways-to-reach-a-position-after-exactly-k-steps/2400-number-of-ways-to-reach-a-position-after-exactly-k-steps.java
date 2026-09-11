class Solution {
    public int numberOfWays(int startPos, int endPos, int k) {
        int[][] dp = new int[2*k+1][k+1];

        dp[k][0] = 1;
        
        if(startPos + k < endPos) return 0;
        if((k-(endPos - startPos))%2 != 0) return 0;

        for(int i = 1; i <= k; i++){
            for(int j = k-i+1; j <= k+i-1; j++){
                dp[j-1][i] = (dp[j-1][i] + dp[j][i-1]) % ((int)1e9 + 7);
                dp[j+1][i] = (dp[j+1][i] + dp[j][i-1]) % ((int)1e9 + 7);
            }
        }

        return dp[endPos+k-startPos][k];
    }
}