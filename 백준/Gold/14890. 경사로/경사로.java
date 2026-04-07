import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int N, L;
    static int[][] arr;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        for(int i = 0; i < N; i++){
            if(check(arr[i])) answer++;
        }

        for(int j = 0; j < N; j++){
            int[] col = new int[N];
            for(int i =0 ; i < N; i++){
                col[i] = arr[i][j];
            }
            if(check(col)) answer++;
        }

        System.out.print(answer);
    }

    private static boolean check(int[] line){
        int cnt = 1;
        for(int i = 1; i < line.length; i++){
            if(line[i] == line[i-1]){
                cnt++;
            }

            else if(line[i] == line[i-1] + 1){ // 오르막길
                if(cnt < L) return false;
                cnt = 1;
            }

            else if(line[i] == line[i-1] -1){ // 내리막길
                for(int j = i; j < i+L; j++){
                    if(j >= line.length || line[j] != line[i]) return false;
                }
                i += L-1;
                cnt = 0;
            }

            else{
                return false;
            }
        }

        return true;
    }

}