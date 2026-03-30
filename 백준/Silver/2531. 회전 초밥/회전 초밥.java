import java.io.IOException;
import java.io.BufferedReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class Main{

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] selected = new int[d+1];
        int[] sushi = new int[N];

        for(int i = 0; i < N; i++){
            sushi[i] = Integer.parseInt(br.readLine());
        }

        selected[c] = 1;
        int cnt = 1;
        int max = 1;

        for(int i = 0; i < k; i++){
            if(selected[sushi[i]] == 0){
                cnt++;
            }
            selected[sushi[i]]++;
        }

        max = cnt;

        for(int i = 1; i <N; i++){
            int removeType = sushi[i-1];
            selected[removeType]--;
            if(selected[removeType] == 0){
                cnt--;
            }

            int addType = sushi[(i+k-1)% N];
            if(selected[addType] == 0){
                cnt ++;
            }
            selected[addType] ++;
            
            int current = cnt + (selected[c] == 0 ? 1: 0);
            
            max = Math.max(max, current);
        }
        
        System.out.println(max);
    }
}