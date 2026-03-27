import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Main{
    static int N;
    static int[] arr;
    static boolean[] visited;
    static ArrayList<Integer> list;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N+1];
        for(int i = 1; i <= N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        list = new ArrayList<>();
        visited = new boolean[N+1];

        for(int i = 1; i < N+1; i++){
            visited[i] = true;
            dfs(i, i);
            visited[i] = false;
        }

        Collections.sort(list);
        System.out.println(list.size());
        for(int num : list){
            System.out.println(num);
        }
    }

    public static void dfs(int index, int start){
        if(arr[index] == start){
            list.add(start);
        }

        if(!visited[arr[index]]){
            visited[arr[index]] = true;
            dfs(arr[index], start);
            visited[arr[index]] = false;
        }
    }
}