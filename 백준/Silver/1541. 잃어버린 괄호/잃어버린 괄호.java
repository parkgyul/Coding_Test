import java.io.IOException;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        Queue<String> q = new LinkedList<>();
        Queue<Integer> num = new LinkedList<>();
        q.add("+");

        boolean flag = false;
        int number = 0;
        int total = 0;
        for(int i =0; i < str.length(); i++){
            if(str.charAt(i) == '-'){
                flag = true;
                q.add("-");
                num.add(number);
                number = 0;
            } else if(str.charAt(i) == '+'){
                if(flag) q.add("-");
                else q.add("+");
                num.add(number);
                number = 0;
            } else{
                number = number*10 + (str.charAt(i)-'0');
                if(i== str.length()-1) num.add(number);
            }
        }

        while(!num.isEmpty()){
            int n = num.poll();
            if(q.poll().equals("+")){
                total += n;
            }else{
                total -= n;
            }
        }

        System.out.print(total);

    }
}