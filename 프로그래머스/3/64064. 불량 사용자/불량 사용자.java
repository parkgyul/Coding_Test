import java.util.*;

class Solution {
    static boolean[] checked;
    static Set<String> set;
    public int solution(String[] user_id, String[] banned_id) {        
        checked = new boolean[user_id.length];
        set = new HashSet<>();
        dfs(0, user_id, banned_id);
        return set.size();
    }
    
    static void dfs(int depth, String[] users, String[] banned){
        if(depth == banned.length){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < users.length; i++){
                if(checked[i]) sb.append(i);
            }
            set.add(sb.toString());
            return;
        }
        
        for(int i = 0; i < users.length; i++){
            if(checked[i]) continue;
            
            if(users[i].length() != banned[depth].length()) continue;
            
            boolean flag = true;
            for(int j = 0; j < banned[depth].length(); j++){
                if(banned[depth].charAt(j) == '*') continue;
                
                if(banned[depth].charAt(j) != users[i].charAt(j)){
                    flag = false;
                    break;
                }
            }
            
            if(flag){
                checked[i] = true;
                dfs(depth+1, users, banned);
                checked[i] = false;
            }
        }
    }
}