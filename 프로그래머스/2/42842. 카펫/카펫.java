class Solution {
    public int[] solution(int brown, int yellow) {
        
        int sum = brown + yellow;
        int n, m= 1; // 세로, 가로
        for(n = 1; n <= Math.sqrt(sum); n++){
            if(sum % n != 0) continue;
        
            m = sum / n;    
            if((n-2) *(m-2) == yellow && 2*(n-2) + 2 * (m-2) + 4 == brown) break;
        }
        
        return new int[]{m, n};
    }
}