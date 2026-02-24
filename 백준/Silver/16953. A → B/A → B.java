import java.io.IOException;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int before = Integer.parseInt(st.nextToken());
        int after = Integer.parseInt(st.nextToken());
        int cnt = 1;

        while(after != before){
            if(after < before){
                System.out.print(-1);
                return;
            }

            if(after%2 == 0){ // 짝수일때
                after /= 2;
                cnt ++;
            }
            // 홀수일떄
            else if(after%10 == 1){ // 끝자리 수 1
                after /= 10;
                cnt ++;
            }
            else{ // 끝자리 수 1 아닐때
                System.out.print(-1);
                return;
            }
        }

        System.out.print(cnt);
    }
}