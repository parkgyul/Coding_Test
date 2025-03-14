import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.HashSet;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean flag, flag_no;
        Set<Character> shortcut = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            flag = false;
            flag_no = false;
            String[] spl_str = str.split(" ");

            for (int j = 0; j < spl_str.length; j++) {
                Character ch = spl_str[j].charAt(0);
                if (!shortcut.contains(ch)) {
                    shortcut.add(Character.toLowerCase(ch));
                    shortcut.add(Character.toUpperCase(ch));
                    spl_str[j] = "["+ ch + "]" + spl_str[j].substring(1);
                    flag = true;
                    flag_no = true;
                    break;
                }
            }
            if(flag){
                for(int j = 0; j < spl_str.length; j++){
                    sb.append(spl_str[j]).append(" ");
                }
                sb.append("\n");
            }
            if (!flag) {
                for (int j = 1; j < str.length(); j++) { //돌아가면서 찾는경우
                    Character c = str.charAt(j);
                    if (c != ' '&&!shortcut.contains(c)) {
                        shortcut.add(Character.toLowerCase(c));
                        shortcut.add(Character.toUpperCase(c));
                        int index = str.indexOf(c);
                        sb.append(str.substring(0, index)).append("[").append(c).append("]").append(str.substring(index + 1)).append("\n");
                        flag_no = true;
                        break;
                    }
                }
            }
            if (!flag_no) sb.append(str).append("\n");
        }
        System.out.println(sb);
    }
}