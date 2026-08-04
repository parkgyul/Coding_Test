import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        
        Point[] points = new Point[N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            points[i] = new Point(x1, x2);
        }

        Arrays.sort(points);

        int[] L = new int[N];
        int[] R = new int[N];

        L[0] = points[0].x2;
        for(int i = 1; i < N; i++){
            L[i] = Math.max(L[i-1], points[i].x2);
        }

        R[N-1] = points[N-1].x2;
        for(int i = N-2; i >= 0; i--){
            R[i] = Math.min(R[i+1], points[i].x2);
        }

        int ans = 0;
        for(int i = 0; i < N; i++){
            if(i >0 && L[i-1] >= points[i].x2){
                continue;
            }

            if(i < N-1 && R[i+1] <= points[i].x2){
                continue;
            }

            ans++;
        }

        System.out.print(ans);
    }

    static class Point implements Comparable<Point>{
        int x1, x2;

        Point(int x1, int x2){
            this.x1 = x1;
            this.x2 = x2;
        }

        public int compareTo(Point o){
            return this.x1 - o.x1;
        }
    }
}