import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Main{

    static int n,m ;
    static Character[][] arr;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int w = 0, b =0;
    static int sum ;
    static boolean[][] visited;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        arr = new Character[n][m];
        visited = new boolean[n][m];

        //값 입력받기
        for(int i = 0; i<n ; i++){
            String str = br.readLine();
            for(int j =0; j<m; j++){
                arr[i][j] = str.charAt(j);
            }
        }


        for(int i= 0; i<n; i++){ //세로
            for(int j = 0; j<m; j++){ //가로
                sum = 0;
                if(!visited[i][j]){
                    if(arr[i][j] == 'W')
                    {
                        sum = search(i,j, 'W');
                        w += sum*sum;
                    }
                    else
                    {
                        sum = search(i,j, 'B');
                        b += sum*sum;
                    }

                }

            }
        }
        System.out.println(w + " "+ b);
    }

    public static int search(int x, int y, Character ch){
        int sum =1;
        Queue<Point> q = new LinkedList<>();

        visited[x][y]= true;
        q.add(new Point(x,y));

        while(!q.isEmpty()){
            Point current = q.poll();
            for(int i = 0 ; i<4; i++){
                int next_x = current.x + dx[i];
                int next_y = current.y + dy[i];

                if(next_x<0 || next_x>= n|| next_y<0 || next_y>= m || arr[next_x][next_y] != ch )
                    continue;
                if(visited[next_x][next_y])
                    continue;

                q.add(new Point(next_x, next_y));
                visited[next_x][next_y] = true;
                sum++;
            }
        }
        return sum;
    }
}