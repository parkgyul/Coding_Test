class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        
        int[][] dp = new int[triangle.length][triangle[triangle.length-1].length];
        
        for(int j = 0; j < triangle[triangle.length-1].length; j++){
            dp[triangle.length-1][j] = triangle[triangle.length-1][j];
        }
        
        for(int i = triangle.length-1; i >= 1; i--){
            for(int j = 0; j < i; j++){
                dp[i-1][j] = triangle[i-1][j] + Math.max(dp[i][j], dp[i][j+1]);
            }
        }
        return dp[0][0];
    }
}