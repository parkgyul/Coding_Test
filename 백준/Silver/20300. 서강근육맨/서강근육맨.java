import java.awt.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;


class Main{
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        long[] arr = new long[n];
        for(int i = 0; i<n; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        long max = Long.MIN_VALUE;

        if(n%2 == 0){
            for(int i = 0; i < n/2; i++){
                max = Math.max(max, arr[i]+arr[n-1-i]);
            }
        }
        else{
            for(int i = 0; i< n/2; i++){
                max = Math.max(max, arr[i] + arr[n-2-i]);
            }
            max = Math.max(max, arr[n-1]);
        }

        System.out.println(max);
    }

}
