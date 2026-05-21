import java.io.*; 
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        
        System.out.print(N == 1 ? 0 : bfs(N));
    }

    static int bfs(int N){
        boolean[] visited = new boolean[2000000];
        Queue<Info> q = new LinkedList<>();
        q.add(new Info(N, 0));

        while(!q.isEmpty()){
            Info info = q.poll();
            int num = info.num;
            visited[num] = true;
            
            if(num == 0 || num == 3 || num == 2){
                return info.cnt+1;
            }

            if(num%2 == 0){
                if(!visited[num/2]){
                    visited[num/2] = true;
                    q.add(new Info(num/2, info.cnt+1));
                }
            }
            if(num%3 == 0){
                if(!visited[num/3]){
                    visited[num/3] = true;
                    q.add(new Info(num/3, info.cnt+1));
                }
            }
            if(!visited[num+1]){
                visited[num+1] = true;
                q.add(new Info(num+1, info.cnt+1));
             }
            if(!visited[num-1]){
                visited[num-1] = true;
                q.add(new Info(num-1, info.cnt+1));
             }
        }

        return -1;
    }

    static class Info{
        int num, cnt;
        public Info(int num, int cnt){
            this.num = num;
            this.cnt = cnt;
        }
    }
}