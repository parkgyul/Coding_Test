import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();

        st  = new StringTokenizer(br.readLine());
        for(int i = 0 ; i<n; i++){
            map.put(Integer.parseInt(st.nextToken()), true);
        }

        int m = Integer.parseInt(br.readLine());
        st  = new StringTokenizer(br.readLine());
        for(int j = 0 ; j< m; j++){
            if(map.containsKey(Integer.parseInt(st.nextToken())))
                System.out.println(1);
            else
                System.out.println(0);
        }

    }
}