import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        int[] prev = new int[n];
        int[] next = new int[n];
        
        for(int i = 0; i < n; i++){
            prev[i] = i-1;
            next[i] = i+1;
        }
        
        next[n-1] = -1;
        
        int cur = k;
        
        Stack<Integer> st = new Stack<>();
        
        for(String str : cmd){
            String[] parts = str.split(" ");
            String c = parts[0];
            
            if(c.equals("D")){
                int t = Integer.parseInt(parts[1]);
                
                for(int i = 0; i < t; i++){
                    cur = next[cur];   
                }
                
            }else if(c.equals("C")){
                int prevNode = prev[cur];
                int nextNode = next[cur];
                
                st.push(cur);
                
                if(prevNode != -1){
                    next[prevNode] = nextNode;
                }
                
                if(nextNode != -1){
                    prev[nextNode] = prevNode;
                }
                
                if(nextNode != -1){
                    cur = nextNode;
                }else{
                    cur = prevNode;
                }
                
            }else if(c.equals("U")){
                int t = Integer.parseInt(parts[1]);
                
                for(int i = 0; i < t; i++){
                   cur = prev[cur];   
                }
            }else{
                int restored = st.pop();
                
                int prevNode = prev[restored];
                int nextNode = next[restored];
                
                if(prevNode != -1){
                    next[prevNode] = restored;
                }
                
                if(nextNode != -1){
                    prev[nextNode] = restored;
                }
            }
        }
        
        char[] answer = new char[n];
        Arrays.fill(answer, 'O');
        
        while(!st.isEmpty()){
            answer[st.pop()] = 'X';
        }
        
        return new String(answer);
    }
}