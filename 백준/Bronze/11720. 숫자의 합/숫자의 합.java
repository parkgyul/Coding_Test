import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

class Main
{
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        br.readLine();
        String str = br.readLine();
        int num = 0 ;
        for(int i = 0; i< str.length(); i++){
            num += Integer.parseInt(str.substring(i,i+1));
        }
        System.out.println(num);


    }
}