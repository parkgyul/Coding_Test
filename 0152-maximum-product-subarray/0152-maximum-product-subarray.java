class Solution {
    public int maxProduct(int[] nums) {
        int max = nums[0];
        int min = nums[0];
        int answer = nums[0];

        for(int i = 1; i < nums.length; i++){
            int prevMax = max;
            int prevMin = min;

            min = Math.min(nums[i], 
                Math.min(prevMax*nums[i], prevMin*nums[i]));

            max = Math.max(nums[i],
                Math.max(prevMax*nums[i], prevMin*nums[i]));

            answer = Math.max(answer, max);
        }

        return answer;
    }
}