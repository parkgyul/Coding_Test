import java.util.Arrays;
class Solution {
    static int[] diff;
    public int solution(int distance, int[] rocks, int n) {
        int l = rocks.length;
        diff = new int[l+1];
        
        Arrays.sort(rocks);
        
        diff[0] = rocks[0];
        diff[l] = distance - rocks[l-1];
        
        for(int i = 1; i < l; i++){
            diff[i] = rocks[i] - rocks[i-1];
        }
    
        int answer = 0;
        int left = 0, right = distance;
        
        while(left <= right){
            int mid = (left+right) / 2;
            
            if(checkPossibility(mid, n)){
                answer = Math.max(mid, answer);
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        
        
        return answer;
    }
    
    static boolean checkPossibility(int mid, int n){
        int cnt = 0;
        int temp = 0;

        for(int i = 0; i < diff.length; i++){ // <- diff.length - 1이 아니라 diff.length
            temp += diff[i];

            if(temp >= mid){
                temp = 0;
            } else {
                cnt++;
            }
        }

        return cnt <= n;
    }
}