import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        
        int[] crewTimes = new int[timetable.length];
        
        for(int i = 0 ; i < timetable.length; i++){
            crewTimes[i] = toMinutes(timetable[i]);
        }
        
        Arrays.sort(crewTimes);
        
        int crewIndex = 0;
        int answer = 0;
        
        for(int bus = 0; bus < n; bus++){
            int startTime = 540 + bus * t;
            int cnt = 0;
            int lastBoardedTime = -1;
            
            while(crewIndex < crewTimes.length 
                  && crewTimes[crewIndex] <= startTime
                  && cnt < m){
                lastBoardedTime = crewTimes[crewIndex];
                crewIndex++;
                cnt++;
            }
            
            if(bus == n-1){ // 마지막 버스
                if(cnt < m){
                    answer = startTime;
                }else{
                    answer = lastBoardedTime -1;
                }
            }
        }
        
        return toTime(answer);
    }
    
    private static int toMinutes(String time){
        String[] arr = time.split(":");
        
        int hour = Integer.parseInt(arr[0]);
        int min = Integer.parseInt(arr[1]);
        
        return 60*hour + min;
    }
    
    private String toTime(int min){
        int hour = min/60;
        int minute = min%60;
        
        return String.format("%02d:%02d", hour, minute);
    }
}