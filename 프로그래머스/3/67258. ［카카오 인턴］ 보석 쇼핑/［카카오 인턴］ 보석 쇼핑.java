import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = {};
        Set<String> set = new HashSet<>();
        
        for(String gem : gems){
            set.add(gem);
        }
        
        int total = set.size();
        
        Map<String, Integer> map = new HashMap<>();
        map.put(gems[0], 1);
        
        int left = 0;
        int right = 0;
        
        int min = gems.length;
        int minLeft = 1;
        int minRight = gems.length;
        
        while(left <= right){
            if(map.size() == total){
                if(min > right - left +1){
                    min = right - left + 1;
                    minLeft = left+1;
                    minRight = right+1;
                }
                
                if(map.get(gems[left]) == 1){
                    map.remove(gems[left]);
                }else{
                    map.put(gems[left], map.get(gems[left]) - 1);
                }
                left++;
            }else{
                if(right >= gems.length-1) break;
                right++;
                map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);
            }
        }
    
        return new int[]{minLeft, minRight};
    }
}