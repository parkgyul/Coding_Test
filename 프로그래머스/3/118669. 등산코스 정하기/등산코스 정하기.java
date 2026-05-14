import java.util.*;
class Solution {
    static Set<Integer> summitSet;
    static Set<Integer> gateSet;
    static List<Path>[] paths;
    public int[] solution(int n, int[][] pathsInput, int[] gates, int[] summits) {
        int[] answer = new int[2];
        
        summitSet = new HashSet<>();
        gateSet = new HashSet<>();
        paths = new ArrayList[n+1];
        
        for(int g : gates){
            gateSet.add(g);
        }
        
        for(int s : summits){
            summitSet.add(s);
        }
        
        for(int i = 1; i < n+1; i++){
            paths[i] = new ArrayList<>();
        }
        
        for(int[] p : pathsInput){
            int to = p[0];
            int from = p[1];
            int inten = p[2];
            
            paths[to].add(new Path(from, inten));
            paths[from].add(new Path(to, inten));
        }
        
        int[] intensity = dijkstra(n);
        
        Arrays.sort(summits);
        
        int summitMin = 0;
        int intensityMin = Integer.MAX_VALUE;
        for(int s : summits){
            if(intensityMin > intensity[s]){
                intensityMin = intensity[s];
                summitMin = s;
            }
        }
        
        answer[0] = summitMin;
        answer[1] = intensityMin;
        return answer;
    }
    
    public int[] dijkstra(int n){
        PriorityQueue<Path> pq = new PriorityQueue<>();
        int[] intensity = new int[n+1];
        Arrays.fill(intensity, Integer.MAX_VALUE);
        for(int gate : gateSet){
            intensity[gate] = 0;
            pq.add(new Path(gate, 0));
        }
        
        while(!pq.isEmpty()){
            Path cur = pq.poll();
            
            if(cur.intensity > intensity[cur.place])
                continue;
            
            if(summitSet.contains(cur.place)) continue;
            
            for(Path nextPath : paths[cur.place]){
                if(gateSet.contains(nextPath.place)) continue;
                
                int nextIntensity = Math.max(nextPath.intensity, cur.intensity);
                
                if(nextIntensity >= intensity[nextPath.place]) continue;
                
                intensity[nextPath.place] = nextIntensity;
                pq.add(new Path(nextPath.place , nextIntensity));
            }
        }
        
        return intensity;
    }
    
    public class Path implements Comparable<Path>{
        int place, intensity;
        
        public Path(int place, int intensity){
            this.place = place;
            this.intensity = intensity;
        }
        
        public int compareTo(Path o){
            return this.intensity - o.intensity;
        }
    }
}