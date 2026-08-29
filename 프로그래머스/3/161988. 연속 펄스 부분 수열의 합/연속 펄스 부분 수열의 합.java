class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        
        long[] evenDp = new long[sequence.length];
        long[] oddDp = new long[sequence.length];
        
        evenDp[0] =  (long) -sequence[0];
        oddDp[0] = (long) sequence[0];
        
        long max = Math.max(evenDp[0], oddDp[0]);
        
        for(int i = 1; i < sequence.length; i++){
            evenDp[i] = Math.max(evenDp[i-1] + (i % 2 == 0 ? -1 : 1)*sequence[i], (i % 2 == 0 ? -1 : 1)*sequence[i]);
            oddDp[i] = Math.max(oddDp[i-1] + (i % 2 == 0 ? 1 : -1)*sequence[i], (i % 2 == 0 ? 1 : -1)*sequence[i]);
            
            if(evenDp[i] > max) max = evenDp[i];
            if(oddDp[i] > max) max = oddDp[i];
        }
        
        
        
        return max;
    }
}