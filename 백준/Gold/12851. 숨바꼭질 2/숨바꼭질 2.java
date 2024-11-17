import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{

    static int subin, sister;
    static boolean[] visited = new boolean[100001];

    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());

        subin = Integer.parseInt(st.nextToken());
        sister = Integer.parseInt(st.nextToken());

        search(subin, sister);
    }
    static void search(int subin, int sister){
        Queue<Integer> q = new LinkedList<>();
        q.add(subin);
        visited[subin] = true;
        boolean flag = false;
        int time =0;
        while(true){
            int size = q.size();
            for(int i = 0; i<size; i++){
                int current = q.poll();
                visited[current] = true;

                if(current == sister){
                    cnt ++;
                    continue;
                }
                if(current*2 <= 100000 && !visited[current*2]) {
                    q.add(2* current);

                }
                if(current+1 <= 100000 && !visited[current+1]){
                    q.add(current +1 );

                }
                if(current-1 >= 0 && !visited[current-1]){
                    q.add(current -1);

                }
            }
            if(cnt >0) {System.out.println(time); System.out.println(cnt); return;}
            time++;
        }
    }
}