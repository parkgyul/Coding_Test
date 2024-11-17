import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;



public class Main{
    static int subin, sister;
    static boolean[] visited= new boolean[100001];
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        subin = Integer.parseInt(st.nextToken());
        sister = Integer.parseInt(st.nextToken());

        search(subin);
    }
    public static void search(int subin){
        Queue<Point> q = new LinkedList<>();

        q.add(new Point(subin, 0));

        while(!q.isEmpty()){
            Point current = q.poll();
            int current_location = current.x;
            int current_time = current.y;

            visited[current_location] = true;

            if(current_location == sister){
                System.out.println(current_time);
                return;
            }

            if(current_location *2 <= 100000 && !visited[2*current_location]){
                q.add(new Point(2*current_location, current_time));
            }
            if(current_location-1 >= 0 && !visited[current_location-1]){
                q.add(new Point(current_location-1, current_time+1));
            }
            if(current_location+1 <= 100000 && !visited[current_location+1]){
                q.add(new Point(current_location+1, current_time+1));
            }
        }


    }
}