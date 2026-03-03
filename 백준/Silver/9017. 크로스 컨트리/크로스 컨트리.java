import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import java.util.ArrayList;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<t; i++){// 테스트 케이스
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] arr = new int[n]; // 입력되는 배열 저장
            Map<Integer, Integer> count = new HashMap<>(); // 각 팀별 선수(명)
            for(int j = 0; j<n; j++){
                arr[j] = Integer.parseInt(st.nextToken());
                count.put(arr[j], count.getOrDefault(arr[j], 0)+1);
            }

            Map<Integer, List<Integer>> scoreMap = new HashMap<>();
            int score = 1;

            for(int j = 0; j < n; j++){
                int team = arr[j];

                if(count.get(team) >= 6){
                    scoreMap.putIfAbsent(team, new ArrayList<>());
                    scoreMap.get(team).add(score++);
                }
            }

            int winner = -1;
            int minScore = Integer.MAX_VALUE;
            int minFifth = -1;

            for(int team: scoreMap.keySet()){
                List<Integer> scores = scoreMap.get(team);

                int sum = 0;
                for(int k = 0; k <4; k++){
                    sum += scores.get(k);
                }
                int fifth = scores.get(4);

                if(sum < minScore){
                    minScore = sum;
                    winner = team;
                    minFifth = fifth;
                }else if(sum == minScore){
                    if(fifth < minFifth){
                        winner = team;
                        minFifth = fifth;
                    }
                }

            }
            sb.append(winner).append("\n");
        }
        System.out.print(sb);
    }
}