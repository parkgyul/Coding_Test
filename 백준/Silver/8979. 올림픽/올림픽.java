import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int team = Integer.parseInt(st.nextToken());
            map.putIfAbsent(team, new ArrayList<>());
            for(int j = 0; j<3; j++){
                map.get(team).add(Integer.parseInt(st.nextToken()));
            }
        }
        int cnt = 0;
        for(int team: map.keySet()){
            if(k == team) continue;

            if(map.get(k).get(0) < map.get(team).get(0)){
                cnt++;
            }else if(map.get(k).get(0).equals(map.get(team).get(0))){
                if(map.get(k).get(1) < map.get(team).get(1)){
                    cnt++;
                }else if(map.get(k).get(1).equals(map.get(team).get(1))){
                    if(map.get(k).get(2) < map.get(team).get(2)){
                        cnt++;
                    }
                }
            }
        }

        System.out.print(1+cnt);
    }
}