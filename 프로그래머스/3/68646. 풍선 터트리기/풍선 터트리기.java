class Solution {
    public int solution(int[] a) {
        int answer = 1;
        int len = a.length;
        
        if(len <= 3) return len;
        
        int minValue = Integer.MAX_VALUE;
        int minIndex = -1;
        
        for(int i =0 ; i < a.length; i++){
            if(minValue > a[i]){
                minValue = a[i];
                minIndex = i;
            }
        }
        
        int min = Integer.MAX_VALUE;
        
        for(int i = 0; i < minIndex; i++){
            if(min > a[i]){
                min = a[i];
                answer++;
            }
        }
        
        min = Integer.MAX_VALUE;
        for(int i = len-1 ; i >= minIndex+1; i--){
            if(min > a[i]){
                answer++;
                min = a[i];
            }
        }
        
        return answer;
    }
}