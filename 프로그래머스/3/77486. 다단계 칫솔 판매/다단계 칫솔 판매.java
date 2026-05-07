import java.util.*;

class Solution {
    static Map<String, String> map;
    static Map<String, Integer> amounts;
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];

        map = new HashMap<>();
        amounts = new HashMap<>();
        for(int i =0; i < enroll.length; i++){
            map.put(enroll[i], referral[i]);
            amounts.put(enroll[i], 0);
        }
        
        
        for(int i =0 ; i < seller.length; i++){
            String s = seller[i];
            int a = amount[i]*100;
                  
            dfs(s, a);
        }
        
        for(int i = 0; i < enroll.length; i++){
            String name = enroll[i];
            int cost = amounts.get(name);
            
            answer[i] = cost;
        }
        
        return answer;
    }
    
    public void dfs(String s, int a){
        amounts.put(s, (amounts.get(s) + (a < 10 ? a : a - (int)(0.1*a))));
        
        String next = map.get(s);
        
        if(next.equals("-") || a < 10) return;

        dfs(next, (int)(0.1 * a));
    }
}