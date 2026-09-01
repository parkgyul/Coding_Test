import java.util.*;

class Solution {
    static Map<String, Integer> map;
    static Set<Integer> courseSet;
    static int courseMax;
    public String[] solution(String[] orders, int[] course) {
        
        courseMax = 1; 
        courseSet = new HashSet<>();
        for(int c : course){
            courseSet.add(c);
            if(courseMax < c) courseMax = c;
        }
        
        map = new HashMap<>();
        
        for(String order : orders){
            char[] orderArr = order.toCharArray();
            Arrays.sort(orderArr);
            boolean[] checked = new boolean[orderArr.length];
            
            dfs(0, 0, orderArr, checked);
        }
        
        int[] max = new int[11];
        List<String>[] list = new ArrayList[11];
        for(int i = 1; i <= 10; i++){
            list[i] = new ArrayList<>();
        }
        
        for(String key : map.keySet()){
            int length = key.length();
            int total = map.get(key);
            if(total < 2) continue;
            if(max[length] < total){
                max[length] = total;
                list[length] =  new ArrayList<>();
                list[length].add(key);
            }else if(max[length] == total){
                list[length].add(key);
            }
        }
        
        List<String> result = new ArrayList<>();
        for(int i = 2; i <= 10; i++){
            if(list[i].size() == 0) continue;
            
            result.addAll(list[i]);
        }
        
        Collections.sort(result);
        
        
        return result.toArray(new String[result.size()]);
    }
    
    static void dfs(int depth, int index, char[] orders, boolean[] checked){
        if(courseSet.contains(depth)){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < checked.length; i++){
                if(!checked[i]) continue;
                sb.append(orders[i]);
            }
            
            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0)+1);
        }
    
        if(depth+1 > courseMax){
            return;
        }
        
        for(int i = index; i < orders.length; i++){
            if(checked[i]) continue;
            
            checked[i] = true;
            dfs(depth+1, i+1, orders, checked);
            checked[i] = false;
        }
        
    }
}

