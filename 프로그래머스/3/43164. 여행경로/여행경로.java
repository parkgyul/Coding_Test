import java.util.*;

class Solution {
    static Map<String, List<String>> graph = new HashMap<>();
    static List<String> path = new ArrayList<>();
    static int totalTickets;

    public String[] solution(String[][] tickets) {
        totalTickets = tickets.length;

        // 1. 그래프 생성
        for (String[] ticket : tickets) {
            graph.putIfAbsent(ticket[0], new ArrayList<>());
            graph.get(ticket[0]).add(ticket[1]);
        }

        // 2. 도착지 목록을 사전순(알파벳순)으로 정렬
        for (List<String> destinations : graph.values()) {
            Collections.sort(destinations);
        }

        // 3. ICN 출발 DFS
        dfs("ICN");

        return path.toArray(new String[0]);
    }

    private boolean dfs(String current) {
       path.add(current);
        
        if(path.size() == totalTickets + 1){
            return true;
        }
        
        List<String> destinations = graph.get(current);
        
        if(destinations != null){
            for(int i = 0; i < destinations.size(); i++){
                String next = destinations.get(i);
                destinations.remove(i);
                
                if(dfs(next)) return true;
                
                destinations.add(i, next);
            }
        }
        
        path.remove(path.size() -1);
        return false;
    }
}