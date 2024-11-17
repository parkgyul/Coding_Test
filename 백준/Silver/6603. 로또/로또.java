//시작점 넣은 코드
import java.io.IOException;
import java.io.BufferedReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class Main{
    static int num;
    static int[] arr;
    static StringBuilder result;
    static boolean[] visited;
    static int[] lotto;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        num = Integer.parseInt(st.nextToken());
        while(num != 0){
            arr = new int[num];
            visited = new boolean[num];
            lotto = new int[6];
            for(int i = 0; i<num; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            result = new StringBuilder();
            dfs(0,0);
            System.out.println(result);

            //새로운 입력 받기
            st = new StringTokenizer(br.readLine());
            num = Integer.parseInt(st.nextToken());
        }
    }
    static void dfs(int start, int depth){

        if(depth == 6){
            for(int a : lotto){
                result.append(a).append(" ");
            }
            result.append("\n");
            return;
        }

        for(int i = start; i<num; i++){
            if(!visited[i]){
                visited[i] = true;
                lotto[depth] = arr[i];
                dfs(i+1, depth+1);
                visited[i] = false;
            }
        }
    }
}