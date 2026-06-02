import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        char ch = str.charAt(0);
        int lastSameIdx = -1;
        for(int i = str.length()-1; i > 0; i--){
            if(str.charAt(i) == ch){
                lastSameIdx = i;
                continue;
            }

            break;
        }

        if(lastSameIdx != -1){
            str = str.substring(lastSameIdx) + str.substring(0, lastSameIdx);
        }

        int total = 0;

        char temp = str.charAt(0);
        int cnt = 1;
        for(int i = 1; i < str.length(); i++){
            if(temp == str.charAt(i)){
                cnt++;
            }
            else{
                temp = str.charAt(i);
                total+= 2;
                cnt = 1;
            }
        }
        if(cnt >= 10) total+= 3;
        else total += 2;

        System.out.print(total);
    }
}