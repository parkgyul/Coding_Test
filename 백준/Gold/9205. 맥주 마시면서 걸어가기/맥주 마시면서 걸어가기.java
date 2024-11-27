import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

import static java.lang.Math.abs;

class Main{
    static Place[] places;
    static int n;
    static Queue<Place> q;
    static boolean[] visited;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        int t = Integer.parseInt(br.readLine());

        for(int i = 0; i < t; i++){
            n = Integer.parseInt(br.readLine());

            places = new Place[n+2];
            visited = new boolean[n+2];

            for(int j = 0; j<n+2; j++){
                st = new StringTokenizer(br.readLine());
                places[j] = new Place(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            }
            if(bfs()) System.out.println("happy");
            else System.out.println("sad");
        }

    }
    public static boolean bfs(){
        q = new LinkedList<Place>();
        q.add(places[0]);
        visited[0] = true;

        while(!q.isEmpty()){
            Place current = q.poll();

            for(int i = 0; i< places.length; i++){
                if(visited[i])
                    continue;
                if((Math.abs(current.x - places[i].x)+ Math.abs(current.y - places[i].y)) > 1000)
                    continue;

                if(i == places.length-1)
                    return true;

                q.add(places[i]);
                visited[i] = true;
            }
        }

        return false;
    }
    public static class Place{
        private int x, y;
        public Place(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

}
