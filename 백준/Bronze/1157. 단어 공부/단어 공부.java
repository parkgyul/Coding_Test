import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.HashMap;

class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine().toUpperCase();

        int[] cnt = new int[26];
        for(int i = 0; i < str.length(); i++){
            int index = str.charAt(i) - 'A';
            cnt[index]++;
        }

        int max = -1;
        int count = 0;

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < 26; i++){
            if(max < cnt[i]){
                sb = new StringBuilder();
                max = cnt[i];
                sb.append((char)(i + 'A'));
                count = 0;
            }
            else if(max == cnt[i]){
                count ++;
            }
        }
        System.out.print(count == 0 ? sb : "?");
    }
}