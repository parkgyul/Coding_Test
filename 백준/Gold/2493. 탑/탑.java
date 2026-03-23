import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main{

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        Stack<Top> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            int height = Integer.parseInt(st.nextToken());

            if(stack.isEmpty()){
                sb.append("0 ");
                stack.push(new Top(i, height));
            }else{
                while(true){
                    if(stack.isEmpty()){
                        sb.append("0 ");
                        stack.push(new Top(i, height));
                        break;
                    }
                    Top top = stack.peek();

                    if(top.height > height){
                        sb.append(top.num + " ");
                        stack.push(new Top(i, height));
                        break;
                    }else{
                        stack.pop();
                    }
                }
            }
        }

        System.out.print(sb);
    }

    public static class Top{
        int num;
        int height;
        public Top(int num, int height){
            this.num = num;
            this.height = height;
        }
    }
}