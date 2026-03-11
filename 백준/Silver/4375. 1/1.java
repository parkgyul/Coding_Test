import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str;
        StringBuilder sb = new StringBuilder();
        while((str = br.readLine()) != null){
            int num = Integer.parseInt(str);

            int min_num = 1;
            int cnt = 1;

            while (min_num % num != 0) {
                min_num = (min_num * 10 + 1) % num;
                cnt++;
            }

            sb.append(cnt).append("\n");
        }
        System.out.print(sb);
    }
}