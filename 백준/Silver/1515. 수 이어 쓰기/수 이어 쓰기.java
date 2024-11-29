import java.awt.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

class Main{
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        
        int j = 0;
        int findNum = 0;

        while(findNum++ <= 30000){
            String temp = String.valueOf(findNum);
            for(int i = 0; i< temp.length(); i++){
                if(temp.charAt(i) == str.charAt(j))
                    j++;
                if(j == str.length())
                {
                    System.out.println(findNum);
                    return;
                }
            }
        }
    }
}
