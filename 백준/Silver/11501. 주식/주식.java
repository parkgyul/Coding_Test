import java.io.IOException;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<n; i++){
            // 정보 입력 받기
            int m = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr = new int[m];
            for(int j = 0; j <m; j++){
                arr[j] = Integer.parseInt(st.nextToken());
            }

            // 이익 구하기
            int max = Integer.MIN_VALUE;
            long total = 0;
            for(int j = m-1; j>=0; j--){
                if(max < arr[j]){
                    max = arr[j];
                    continue;
                }

                total += (max - arr[j]);
            }

            sb.append(total).append("\n");
        }

        System.out.print(sb);
    }
}