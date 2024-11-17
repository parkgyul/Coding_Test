import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{

    static int computer, network;

    static int[][] computers;
    static boolean[] visited;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        computer = Integer.parseInt(br.readLine());
        network = Integer.parseInt(br.readLine());

        computers = new int[computer+1][computer+1];
        visited = new boolean[computer+1];

        for(int i  =0; i <network; i++ ){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            computers[a][b] = computers[b][a] = 1;
        }

        bfs(1);
    }

    public static void bfs(int start){
        int cnt =0;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;
        while(!q.isEmpty()){
            start = q.poll();
            for(int j = 1; j<= computer; j++){
                //방문을 안했거나 연결된 컴퓨터라면
                if(!visited[j]&& computers[start][j] == 1){
                    q.add(j);
                    visited[j] = true;
                    cnt++;
                }
            }
        }

        System.out.print(cnt);

    }


}