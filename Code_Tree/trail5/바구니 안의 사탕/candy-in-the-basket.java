import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Candy[] candies = new Candy[N];
        
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            candies[i] = new Candy(n, l);
        }

        Arrays.sort(candies);

        int max = 0;

        int right = -1;
        int totalNums = 0;
        for(int left = 0; left < N; left++){
            while(right+1 < N && candies[right+1].x - candies[left].x <= 2*K){
                totalNums += candies[right+1].num;
                right++;
            }

            max = Math.max(max, totalNums);

            totalNums -= candies[left].num;
        }

        System.out.print(max);
    }

    static class Candy implements Comparable<Candy>{
        int num, x;

        Candy(int num, int x){
            this.num = num;
            this.x = x;
        }

        public int compareTo(Candy o){
            return this.x - o.x;
        }
    }


}