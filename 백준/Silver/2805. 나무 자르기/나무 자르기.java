import java.io.IOException;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
public class Main{
    static int n; //나무의 수
    static int m; // 집으로 가져가려고 하는 나무의 길이

    static int[] arr;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());


        for(int i = 0; i<n; i++){
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
        }

        Arrays.sort(arr);
        System.out.println(search(0, arr[n-1]));
    }
    public static int search(int low, int high){

        if(low > high){
            return high;
        }
        int mid = (low+high)/2;
        long tree_length = 0;
        for (int i = 0; i < n; i++) {
            long sub = (long)(arr[i] - mid);
            if (sub > 0) tree_length += sub;
        }
        if(tree_length >= m){
            return search(mid+1, high);
        }else{
            return search(low, mid-1);
        }
    }
}