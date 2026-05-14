import java.util.*;
class Solution {
    static List<Integer>[] edges;
    static int[] infos;
    static int max;
    public int solution(int[] info, int[][] edgesInfo) {
        int n = info.length;
        infos = info;
        edges = new ArrayList[n+1];
        
        for(int i = 0; i < n+1; i++){
            edges[i] = new ArrayList<>();
        }
        
        for(int[] edge : edgesInfo){
            int p = edge[0];
            int c = edge[1];
            
            edges[p].add(c);
        }
        
        List<Integer> list = new ArrayList<>();
        list.add(0);
        max = Integer.MIN_VALUE;
        
        dfs(0, 0, list);
        
        return max;
    }
    
    public void dfs(int sheep, int wolf, List<Integer> candidates){
        for(int next : candidates){
            int newSheep = sheep;
            int newWolf = wolf;
            
            if(infos[next] == 0){ // 양
                newSheep ++;
            }else{
                newWolf++;
            }
            
            if(newSheep == newWolf) continue;
            
            max = Math.max(newSheep, max);
            
            List<Integer> newCandidates = new ArrayList<>(candidates);
            newCandidates.remove(Integer.valueOf(next));
            newCandidates.addAll(edges[next]);
            
            dfs(newSheep, newWolf, newCandidates);
        }
    }
}