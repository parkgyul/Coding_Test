import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

class Main{

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashSet<String> notEverHeard = new HashSet<>();
        ArrayList<String> notEverHeardSeen = new ArrayList<>();

        for(int i = 0; i<n; i++){
            notEverHeard.add(br.readLine());
        }
        for(int i = 0; i<m; i++){
            String str = br.readLine();
            if(notEverHeard.contains(str))
                notEverHeardSeen.add(str);
        }

        Collections.sort(notEverHeardSeen);

        System.out.println(notEverHeardSeen.size());

        for(int i = 0; i<notEverHeardSeen.size(); i++){
            System.out.println(notEverHeardSeen.get(i));
        }
    }
}