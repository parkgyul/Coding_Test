import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main{
    static int s;
    static boolean[][] visited = new boolean[2001][2001];

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = Integer.parseInt(br.readLine());

        search(s);
    }
    public static void search(int s){
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(1,0));
        visited[1][0] = true;
        int time = 0;
        while(true){
            int size  = q.size();
            for(int i = 0; i<size; i++){
                Point current = q.poll();
                int current_screen = current.x;
                int current_clip = current.y;


                if(s == current_screen){
                    System.out.println(time);
                    return;
                }
                // 1. 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장한다.
                if(current_screen > 0 && current_screen != current_clip && !visited[current_screen][current_screen]){
                    q.add(new Point(current_screen, current_screen));
                    visited[current_screen][current_screen] = true;
                }
                //2. 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기 한다.
                if(current_clip >0 && current_screen+current_clip<=2000 && !visited[current_screen+current_clip][current_clip]){
                    q.add(new Point(current_screen + current_clip, current_clip));
                    visited[current_screen+current_clip][current_clip] = true;
                }
                //3. 화면에 있는 이모티콘 중 하나를 삭제한다.
                if(current_screen>0 && current_clip >0 && !visited[current_screen-1][current_clip]){
                    q.add(new Point(current_screen -1, current_clip));
                    visited[current_screen-1][current_clip] = true;
                }
            }
            time++;
        }
    }
}