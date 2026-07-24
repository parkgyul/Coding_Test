import java.util.*;

class Solution {
    static int max = 1;
    static List<Integer>[] list;
    static int[] info;
    public int solution(int[] inf, int[][] edges) {
        info = inf;
        
        list = new ArrayList[inf.length];
        
        for(int i = 0; i < inf.length; i++){
             list[i] = new ArrayList<>();
        }
        
        for(int[] e : edges){
            list[e[0]].add(e[1]);
        }
        
        List<Integer> list = new ArrayList<>();
        list.add(0);
        
        dfs(0, 0, list);
        
        return max;
    }
    
    static void dfs(int sheep, int wolf, List<Integer> candidates){
        
        for(int next : candidates){
            int newSheep, newWolf;
            if(info[next] == 0){
                newSheep = sheep+1;
                newWolf = wolf;
            }else{
                newSheep = sheep;
                newWolf = wolf+1;
            }
            
            if(newSheep == newWolf) continue;
            
            max = Math.max(newSheep, max);
            
            List<Integer> newCandidates = new ArrayList<>(candidates);
            newCandidates.remove(Integer.valueOf(next));
            newCandidates.addAll(list[next]);
            
            dfs(newSheep, newWolf, newCandidates);
        }
    }
}