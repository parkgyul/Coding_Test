import java.io.*;
import java.util.*;

class Solution {
    static char[] letters = {'A', 'E', 'I', 'O', 'U'};
    static int answer;
    static boolean flag;
    public int solution(String word) {
        answer = 0;
        
        dfs(word, new StringBuilder(), 0);
        
        return answer;
    }
    
    static void dfs(String target, StringBuilder sb, int depth){
        if(flag) return;
        
        if(target.equals(sb.toString())){
            flag = true;
            return;
        }
        
        if(depth == 5){
            return;
        }
        
        for(int i = 0; i < 5; i++){
            answer++;
            sb.append(letters[i]);
            dfs(target, sb, depth+1);
            sb.deleteCharAt(sb.length()-1);
            
            if(flag) return;
        }
    }
}