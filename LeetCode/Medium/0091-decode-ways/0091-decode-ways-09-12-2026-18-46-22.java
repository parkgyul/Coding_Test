class Solution {
    public int numDecodings(String s) {
        int[] dp = new int[s.length()+1];

        if(s.charAt(0) == '0') return 0;
        
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= s.length(); i++){
            int oneDigit = s.charAt(i-1) - '0';
            if(oneDigit >= 1 && oneDigit <= 9){
                dp[i] += dp[i-1];
            }

            int twoDigit = Integer.parseInt(s.substring(i-2, i));
            if(twoDigit >= 10 && twoDigit <= 26){
                dp[i] += dp[i-2];
            }
        }
        
        return dp[s.length()];
    }
}