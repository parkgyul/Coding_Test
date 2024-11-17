import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
public class Main{
   static int[][] arr ;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        int n = Integer.parseInt(br.readLine());

        arr = new int[n][2];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        //종료 시간을 기준으로 정렬
        sort();

        //제일 빨리 끝나는 것 부터 선택
        int end_day = arr[0][1];
        int total = 1;
        for(int i = 1; i<n; i++){
            if(end_day <= arr[i][0]){
                end_day = arr[i][1];
                total ++;
            }
        }

        System.out.println(total);


    }
    public static void sort(){
        Arrays.sort(arr, (arr_i,arr_j)->{
            //종료 시간이 같다면
            if(arr_i[1] == arr_j[1]) return Integer.compare(arr_i[0], arr_j[0]);
            //종료 시간이 다르다면
            else return Integer.compare(arr_i[1], arr_j[1]);
        });
    }
}