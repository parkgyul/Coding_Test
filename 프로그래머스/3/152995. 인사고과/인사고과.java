import java.util.*;
class Solution {
    public int solution(int[][] scores) {
        int answer = 0;
        
        boolean[] included = new boolean[scores.length];
        
        
        int w0 = scores[0][0];
        int w1 = scores[0][1];
        
        Arrays.sort(scores, (a, b) -> {
            return ((b[0] + b[1])  -  (a[0] + a[1]));
        });
        
        int cnt = 0;
        
        for(int i = 0; i < scores.length; i++){
            int a = scores[i][0];
            int b = scores[i][1];
            
            if(checkPossible(scores, i)){
                included[i] = true;
                cnt++;
            }
            
            
            if(a == w0 && b == w1){ // 해당 사람
                int index = i-1;
                int sameCnt = 0;
                
                if(!included[i]) return -1;
                
                while(index > 0 && included[index] && (scores[index][0] + scores[index][0]) == (w0+w1)){
                    sameCnt++;
                    index--;
                }
                
                return cnt - sameCnt;
            }
        }  
        return -1;
    }
    
    private boolean checkPossible(int[][] scores, int index){
        int index0 = scores[index][0];
        int index1 = scores[index][1];
        
        for(int i = 0; i < index; i++){
            if(scores[i][0] > index0 && scores[i][1] > index1)
                return false;
        }
        
        return true;
    }
}