import java.io.IOException;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
    static public void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int[] arr = new int[w];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i<w; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int total =0;
        for(int i = 1; i < w-1; i++){
            int left_max = Integer.MIN_VALUE;
            int right_max = Integer.MIN_VALUE;
            for(int j = i; j >= 0; j--){ // 좌측으로 최대 값
                left_max = Math.max(left_max, arr[j]);
            }

            for(int j = i; j < w; j++){ // 좌측으로 최대 값
                right_max = Math.max(right_max, arr[j]);
            }

            if(left_max > arr[i] && right_max > arr[i]){
                total += Math.min(left_max, right_max) - arr[i];
            }
        }

        System.out.print(total);
    }
}