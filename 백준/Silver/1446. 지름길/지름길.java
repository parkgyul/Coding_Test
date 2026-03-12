import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        ArrayList<Node> nodes = new ArrayList<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            if(end-start <= distance) continue;
            if(end > D) continue;

            nodes.add(new Node(start, end, distance));
        }

        nodes.sort((a, b) -> a.start - b.start);

        int[] dp = new int[D+1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;
        int index = 0;
        for(int i = 0; i < D; i++){
            // 일반 도로 이동
            dp[i+1] = Math.min(dp[i+1], dp[i]+1);

            // 지름길 처리
            while(index < nodes.size() && nodes.get(index).start == i) {
                Node current = nodes.get(index);
                dp[current.end] = Math.min(dp[current.end], dp[current.start] + current.distance);
                index++;
            }
        }


        System.out.print(dp[D]);
    }

    public static class Node{
        int start, end, distance;

        public Node(int start, int end, int distance){
            this.start = start;
            this.end = end;
            this.distance = distance;
        }
    }
}