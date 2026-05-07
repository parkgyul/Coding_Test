import java.util.*;
class Solution {
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int n = enroll.length;
        
        Map<String, Integer> indexMap = new HashMap<>();
        int[] parent = new int[n];
        int[] answer = new int[n];
        
        for(int i = 0; i < n; i++){
            indexMap.put(enroll[i], i);
        }
        
        for(int i = 0; i < n; i++){
            if(referral[i].equals("-")){
                parent[i] = -1;
            }else{
                parent[i] = indexMap.get(referral[i]);
            }
        }
        
        for(int i = 0; i < seller.length; i++){
            int cur = indexMap.get(seller[i]);
            int money = amount[i] * 100;
            
            while(cur!= -1 && money > 0){
                int give = money/10;
                int keep = money - give;
                
                answer[cur] += keep;
                
                money = give;
                cur = parent[cur];
            }
            
        }
        
        
        
        return answer;
    }
}