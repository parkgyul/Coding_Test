class Solution {
    public long solution(int[] sequence) {
        long[] oddSequence = new long[sequence.length];
        long[] evenSequence = new long[sequence.length];

        for (int i = 0; i < sequence.length; i++) {
            oddSequence[i] = sequence[i];
            evenSequence[i] = sequence[i];

            if (i % 2 == 0) {
                oddSequence[i] *= -1;
            } else {
                evenSequence[i] *= -1;
            }
        }

        long answer = Math.max(oddSequence[0], evenSequence[0]);

        for (int i = 1; i < sequence.length; i++) {
            oddSequence[i] = Math.max(oddSequence[i], oddSequence[i - 1] + oddSequence[i]);
            evenSequence[i] = Math.max(evenSequence[i], evenSequence[i - 1] + evenSequence[i]);

            answer = Math.max(answer, Math.max(oddSequence[i], evenSequence[i]));
        }

        return answer;
    }
}