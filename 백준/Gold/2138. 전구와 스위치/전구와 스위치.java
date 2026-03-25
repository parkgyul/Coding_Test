import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main{
    static int N;
    static char[] origin, target;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        origin = br.readLine().toCharArray();
        target = br.readLine().toCharArray();

        int res1 = solve(false); // 1번 안누름
        int res2 = solve(true); // 1번 누름

        if(res1 == -1 && res2 == -1) System.out.print(-1);
        else if(res1 == -1) System.out.println(res2);
        else if(res2 == -1) System.out.println(res1);
        else System.out.println(Math.min(res1, res2));
    }

    static int solve(boolean pressFirst){
        char[] arr = origin.clone();
        int count = 0;
        if(pressFirst){
            toggle(arr, 0);
            count ++;
        }

        for(int i = 1; i < N; i++){
            if(arr[i-1] != target[i-1]){
                toggle(arr, i);
                count++;
            }
        }
        if(Arrays.equals(arr, target)) return count;
        return -1;
    }

    static void toggle(char[] arr, int i){
        for(int j = i-1; j <= i+1; j++){
            if(j >= 0 && j < N){
                arr[j] = (arr[j] == '0') ? '1' : '0';
            }
        }
    }

}