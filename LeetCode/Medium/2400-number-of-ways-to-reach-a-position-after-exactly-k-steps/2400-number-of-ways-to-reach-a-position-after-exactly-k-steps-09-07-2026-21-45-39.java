class Solution {
    public int numberOfWays(int startPos, int endPos, int k) {
        int[][] dp = new int[2*k+1][k+1];

        dp[k][0] = 1;
        // System.out.println();
        // cnt = 0;
        // dfs(startPos, endPos-1, 0, k);
        // System.out.println(cnt);
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

    // static int cnt ;

    // static void dfs(int num, int target, int depth, int k){
    //     if(depth > k) return; 

    //     if(num == target&& depth == k){
    //         cnt++;
    //         return;
    //     }

    //     dfs(num+1, target, depth+1, k);
    //     dfs(num-1, target, depth+1, k);
    // }
}