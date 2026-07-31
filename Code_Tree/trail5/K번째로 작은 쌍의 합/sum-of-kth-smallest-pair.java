import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] nArr = new int[N];
        int[] mArr = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            nArr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            mArr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nArr);
        Arrays.sort(mArr);

        PriorityQueue<Tuple> pq = new PriorityQueue<>();

        for(int i = 0; i < N; i++){
            pq.add(new Tuple(nArr[i] + mArr[0], i, 0));
        }

        for(int i = 0; i < K-1; i++){
            Tuple bestT = pq.poll();
            int idx1 = bestT.idx1;
            int idx2 = bestT.idx2;

            idx2++;

            if(idx2 < M){
                pq.add(new Tuple(nArr[idx1] + mArr[idx2], idx1, idx2));
            }
        }

        System.out.print(pq.peek().sum);
    }

    static class Tuple implements Comparable<Tuple>{
        int sum, idx1, idx2;

        Tuple(int sum, int idx1, int idx2){
            this.sum = sum;
            this.idx1 = idx1;
            this.idx2 = idx2;
        }

        public int compareTo(Tuple o){
            if(this.sum != o.sum){
                return this.sum - o.sum;
            }else if(idx1 != o.idx1){
                return this.idx1 - this.idx2;
            }else{
                return this.idx2 - o.idx2;
            }
        }
    }
}