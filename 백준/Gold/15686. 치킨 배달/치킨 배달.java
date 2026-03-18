import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main{
    static int N, M;
    static int[][] arr;
    static List<Point> chickenList = new ArrayList<>();
    static List<Point> houseList = new ArrayList<>();
    static boolean[] visited;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken()); // 최대

        arr = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 1) houseList.add(new Point(i,j));
                else if(arr[i][j] == 2) chickenList.add(new Point(i,j));
            }
        }

        visited = new boolean[chickenList.size()];
        for(int i = 0; i < chickenList.size(); i++){
            visited[i] = true;
            dfs(i, 1);
            visited[i] = false;
        }

        System.out.print(min);
    }

    public static void dfs(int start, int depth){
        if(depth == M){
            min = Math.min(min, findMin());
            return;
        }

        for(int i = start+1; i < chickenList.size(); i++){
            visited[i] = true;
            dfs(i, depth+1);
            visited[i] = false;
        }
    }

    public static int findMin(){
        int total = 0;

        for (Point house : houseList) {
            int distance = Integer.MAX_VALUE;

            for (int j = 0; j < chickenList.size(); j++) {
                if (!visited[j]) continue;
                Point chicken = chickenList.get(j);

                distance = Math.min(distance, Math.abs(house.i - chicken.i) + Math.abs(house.j - chicken.j));
            }

            total += distance;
        }

        return total;
    }

    public static class Point{
        int i, j;
        public Point(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
}