import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        String str = br.readLine();
        for(int i = 0; i < n; i++){
            char ch = str.charAt(i);
            if(ch == 'H'){
                arr[i] = 2; // 햄버거
            }else{
                arr[i] = 1; // 사람
            }
        }

        int total= 0;
        for(int i = 0; i <n; i++){
            if(arr[i] == 2){
                for(int j = i-k; j <= i+k; j++){
                    if(j < 0 || j >= n || j == i) continue;

                    if(arr[j] == 1){
                        arr[j] = 0;
                        total++;
                        break;
                    }
                }
            }
        }

        System.out.print(total);
    }
}