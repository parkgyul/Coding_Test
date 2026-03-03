import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        int cnt_0 = 0, cnt_1 = 0;
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '0')
                cnt_0 ++;
            else
                cnt_1 ++;
        }

        cnt_0 /= 2;
        cnt_1 /= 2;

        for(int i = 0; i < str.length(); i++){
            // 1 삭제
            if(cnt_1 > 0){
                if(str.charAt(i) == '1') {
                    str = str.substring(0, i) + str.substring(i+1);
                    cnt_1--;
                    i--;
                }
            }
        }

        for(int i = str.length()-1; i >= 0; i--){
            if(cnt_0 > 0){
                if(str.charAt(i) == '0') {
                    str = str.substring(0, i) + str.substring(i+1);
                    cnt_0--;
                }
            }
        }

        System.out.print(str);
    }
}