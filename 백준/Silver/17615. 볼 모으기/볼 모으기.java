import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.util.StringTokenizer;

class Main{
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int r_cnt = 0, b_cnt = 0;
        for(int i = 0; i<n; i++){
            if(str.charAt(i) == 'R'){
                r_cnt++;
            }else{
                b_cnt++;
            }
        }

        int min = Integer.MAX_VALUE;

        // 1. R을 움직이는 방법
        // 1-1) R을 왼쪽으로 몰기
        int ballCnt = 0;
        for(int i = 0; i < n; i++){
            if(str.charAt(i) == 'R')
                ballCnt++;
            else
                break;
        }
        min = Math.min(min, r_cnt-ballCnt);

        // 1-2). R을 오른쪽으로 몰기
        ballCnt = 0;
        for(int i = n-1; i >= 0; i--){
            if(str.charAt(i) == 'R')
                ballCnt++;
            else
                break;
        }
        min = Math.min(min, r_cnt-ballCnt);

        // 1. B을 움직이는 방법
        // 2-1) B을 왼쪽으로 몰기
        ballCnt = 0;
        for(int i = 0; i < n; i++){
            if(str.charAt(i) == 'B')
                ballCnt++;
            else
                break;
        }
        min = Math.min(min, b_cnt-ballCnt);

        // 1-2). R을 오른쪽으로 몰기
        ballCnt = 0;
        for(int i = n-1; i >= 0; i--){
            if(str.charAt(i) == 'B')
                ballCnt++;
            else
                break;
        }
        min = Math.min(min, b_cnt-ballCnt);

        System.out.print(min);
    }
}