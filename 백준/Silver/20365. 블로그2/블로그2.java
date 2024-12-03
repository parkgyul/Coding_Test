import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int b_cnt = 0;
        int r_cnt = 0;
        char prev_ch = '\n';
        for(int i = 0; i<str.length(); i++){
            char ch = str.charAt(i);
            if(prev_ch != ch){
                if(ch == 'B') b_cnt++;
                else r_cnt++;
                prev_ch = ch;
            }

        }
        System.out.println(Math.min(b_cnt,r_cnt)+1);

    }
}