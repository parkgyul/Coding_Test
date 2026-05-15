class Solution {
    public long solution(int[] sequence) {
        long[] pulseDp1 = new long[sequence.length]; // -1 부터
        pulseDp1[0] = -sequence[0];
        long[] pulseDp2 = new long[sequence.length];
        pulseDp2[0] = sequence[0];
        
         long answer = Math.max(pulseDp1[0], pulseDp2[0]);
        
        for(int i = 1; i < sequence.length; i++){
            int value1 = (i%2 == 0 ? -1 : 1) * sequence[i];
            int value2 = (i%2 == 0 ? 1 : -1) * sequence[i];
            pulseDp1[i] = Math.max(pulseDp1[i-1] + value1, value1);
            pulseDp2[i] = Math.max(pulseDp2[i-1] + value2, value2);
            answer = Math.max(pulseDp1[i], answer);
            answer = Math.max(pulseDp2[i], answer);
        }
        return answer;
    }
}