import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        
        Stack<Integer> st = new Stack<>();
        for(int i = progresses.length-1; i >= 0; i--){
            int progress = progresses[i];
            int speed = speeds[i];
            
            int time = (100-progress)/speed + ((100-progress)%speed != 0 ? 1 : 0);
            
            st.push(time);
        }
        
        int total;
        while(!st.isEmpty()){
            int t = st.pop();
            total = 1;
            while(!st.isEmpty() && st.peek() <= t){
                st.pop();
                
                total++;     
            }    
            answer.add(total);
        }
        
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}