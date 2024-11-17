import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{

    static int subin, sister;
    static int[] visited = new int[100001];


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
        visited[subin] = 1;
        while(!q.isEmpty()){
            int current = q.poll();

            if(current == sister){
                System.out.println(visited[current]-1);
                return;
            }
            if(current-1 >= 0 && visited[current-1] == 0){
                visited[current-1] = visited[current] +1;
                q.add(current-1);
            }
            if(current +1 <= 100000 && visited[current+1] == 0){
                visited[current+1] = visited[current] +1;
                q.add(current+1);
            }
            if(2*current <= 100000 && visited[2*current] == 0){
                visited[2*current] = visited[current] +1;
                q.add(2*current);
            }
        }

    }
}