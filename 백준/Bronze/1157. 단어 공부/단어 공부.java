import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.HashMap;

class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine().toUpperCase();

        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i <str.length(); i++){
            char ch = str.charAt(i);

            if(map.containsKey(ch)){
                map.put(ch, map.get(ch) +1);
            }else{
                map.put(ch, 1);
            }

        }

        int max = Integer.MIN_VALUE;
        for(char ch : map.keySet()){
            max = Math.max(map.get(ch), max);
        }

        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for(char ch : map.keySet()){
            if(map.get(ch).equals(max)){
                sb.append(ch);
                cnt++;
            }
        }

        if(cnt == 1) System.out.print(sb);
        else System.out.print("?");

    }
}