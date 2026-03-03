import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 1; i < n+1; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int student = Integer.parseInt(br.readLine());

        for(int i = 0; i < student; i++){
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int number = Integer.parseInt(st.nextToken());

            int num = number;
            int index = 2;
            if(gender == 1){ // 남학생
                while(num <= n){
                   arr[num] =  arr[num] == 0 ? 1 : 0;
                   num = number * index;
                   index++;
                }
            }else{
                arr[number] =  arr[number] == 0 ? 1 : 0;
                for(int j = 1;; j++){
                    if(number-j < 1 || number+j > n) break;

                    if(arr[number-j] == arr[number+j]){
                        arr[number-j] =  arr[number-j] == 0 ? 1 : 0;
                        arr[number+j] =  arr[number+j] == 0 ? 1 : 0;
                    }else{
                        break;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<n+1; i++){
            sb.append(arr[i]);
            if(i % 20== 0) sb.append("\n");
            else sb.append(" ");
        }

        System.out.print(sb);
    }
}