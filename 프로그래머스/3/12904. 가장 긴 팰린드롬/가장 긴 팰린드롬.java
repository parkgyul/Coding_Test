class Solution
{
    static int max;
    public int solution(String s)
    {
        max = Integer.MIN_VALUE;
        
        for(int i = 0; i < s.length(); i++){
            expand(s, i, i+1);
            expand(s, i, i);
        }

        return max;
    }
    
    public void expand(String s, int left, int right){
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            max = Math.max(max, right - left + 1);
            
            left--;
            right++;
        }
    }
}