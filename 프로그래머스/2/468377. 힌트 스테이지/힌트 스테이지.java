class Solution {
    static int[] hintCnt;
    static int min;
    static int stageCnt;
    public int solution(int[][] cost, int[][] hint) {
        
        stageCnt = cost.length;
        //cost idx = 0
        // hint idx = 0
        // hintCnt idx = 0
        
        hintCnt = new int[stageCnt];
        min = Integer.MAX_VALUE;
        
        dfs(0, 0, cost, hint);
        return min;
    }
    
    static void dfs(int stage, int total, int[][] cost, int[][] hint){
        if(total >= min) return;
        
        if(stage == stageCnt){
            min = total;
            return;
        }
        
        // hint 삼
        if(stage < stageCnt-1){ //마지막 칸은 hint 없음.
            int hintCost = hint[stage][0];
            
            for(int i = 1; i < hint[0].length; i++){
                int hintStage = hint[stage][i] -1 ;
                hintCnt[hintStage] ++;
            }
            
            dfs(stage+1, total + cost[stage][Math.min(hintCnt[stage], stageCnt-1)] + hintCost, cost, hint);
            
            for(int i = 1; i < hint[0].length; i++){
                int hintStage = hint[stage][i] -1;
                hintCnt[hintStage] --;
            }
        }
        
        // 힌트 안 삼
        dfs(stage+1, total+cost[stage][Math.min(hintCnt[stage], stageCnt-1)], cost, hint);
    }
}