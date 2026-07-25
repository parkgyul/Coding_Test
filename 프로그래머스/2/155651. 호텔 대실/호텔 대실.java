import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int[] timeline = new int[1441]; // 하루 = 1440분

        for (String[] time : book_time) {
            int start = toMinute(time[0]);
            int end = toMinute(time[1]) + 10; // 청소시간 포함

            timeline[start]++;
            if (end <= 1440) timeline[end]--;
        }

        int answer = 0, cur = 0;
        for (int i = 0; i <= 1440; i++) {
            cur += timeline[i];
            answer = Math.max(answer, cur);
        }
        return answer;
    }

    private int toMinute(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }
}