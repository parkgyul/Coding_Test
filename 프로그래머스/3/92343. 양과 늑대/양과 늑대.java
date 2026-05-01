import java.util.*;

class Solution {
    static int max;
    static int[] infos;
    static List<Integer>[] trees;
    
    public int solution(int[] info, int[][] edges) {
        infos = new int[info.length];
        infos = info;
        
        trees = new ArrayList[info.length+1];
        for(int i = 0; i < info.length+1; i++){
            trees[i] = new ArrayList<>();
        }
        
        for(int i =0 ; i < edges.length; i++){
            int from = edges[i][0];
            int to = edges[i][1];
            
            trees[from].add(to);
        }
        
        max = Integer.MIN_VALUE;
        
        List<Integer> candidates = new ArrayList<>();
        candidates.add(0);
        
        dfs(0, 0, candidates);
        
        
        return max;
    }
    
    public void dfs(int sheep, int wolf, List<Integer> candidates){
        for(int next : candidates){
            int nextSheep = sheep;
            int nextWolf = wolf;
            
            if(infos[next] == 1){
                nextWolf++;
            }else{
                nextSheep++;
            }
            
            if(nextSheep <= nextWolf)
                continue;
            
            max = Math.max(max, nextSheep);
            
            List<Integer> newCandidates = new ArrayList<>(candidates);
            newCandidates.remove(Integer.valueOf(next));
            newCandidates.addAll(trees[next]);
            
            dfs(nextSheep, nextWolf, newCandidates);
        }
    }
}