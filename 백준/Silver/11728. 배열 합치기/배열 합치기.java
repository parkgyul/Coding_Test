import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<Integer> arr = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for(int j = 0; j < m; j++){
            arr.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(arr);
        Iterator<Integer> iterator = arr.iterator();
        StringBuilder sb = new StringBuilder();
        while(iterator.hasNext()){
            sb.append(iterator.next()).append(" ");
        }

        System.out.println(sb);
    }

}