import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        
        Arrays.sort(routes, (a, b) -> {
            if(a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });
        
        int answer = 1;
        int installed = routes[0][1];
        
        for(int[] r : routes){
            if(r[0] <= installed) continue;
            
            installed = r[1];
            answer++;
        }
        
        return answer;
    }
}