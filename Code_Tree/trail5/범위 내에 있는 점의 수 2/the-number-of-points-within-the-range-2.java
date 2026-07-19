import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i =0 ; i < N; i++){
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
        }
        Arrays.sort(arr);

        int[] start = new int[Q];
        int[] end = new int[Q];

        int max = 0;

        for(int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            start[i] = s;
            end[i] = e;
            max = Math.max(max, e);
        }

        int[] prefix = new int[max+1];
        int idx = 0;
        if(arr[idx] == 0){
            prefix[0] = 1;
            idx++;
        }

        for(int i = 1; i <= max; i++){
            if(idx < N && arr[idx] == i){
                prefix[i] = prefix[i-1] + 1;
                idx++;
            }else{
                prefix[i] = prefix[i-1];
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < Q; i++){
            int s = start[i];
            int e = end[i];
            sb.append(prefix[e] - prefix[(s >= 1 ? s-1 : 0)]).append("\n");
        }

        System.out.print(sb);
    }
}