import java.io.IOException;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), "+-", true);

        int total = Integer.parseInt(st.nextToken()); // 가장 처음은 숫자 가정
        while(st.hasMoreElements()){
            String str = st.nextToken();
            if(str.equals("-")){
                total -= Integer.parseInt(st.nextToken());
                while(st.hasMoreElements()){
                    st.nextToken();
                    total -= Integer.parseInt(st.nextToken());
                }
            } else{
                total += Integer.parseInt(st.nextToken());
            }
        }

        System.out.print(total);

    }
}