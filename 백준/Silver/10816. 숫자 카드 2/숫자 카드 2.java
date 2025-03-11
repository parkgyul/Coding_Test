import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.util.Arrays;

public class Main{
    static int n, m;
    static int[] sang;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        sang = new int[n];
        for(int i = 0; i<n; i++){
            sang[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(sang);

        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<m; i++){
            int find_card = Integer.parseInt(st.nextToken());
            sb.append(findEndIndex(find_card)-findStartIndex(find_card) + " ");
        }

        System.out.println(sb);
    }
    public static int findStartIndex(int find_card){
        int left = 0;
        int right = n;
        while(left<right){
            int mid = (left + right) / 2;
            if(find_card <= sang[mid]){
                right = mid;
            }
            else{
                left = mid +1;
            }
        }
        return right;
    }
    public static int findEndIndex(int find_card){
        int left = 0;
        int right = n;
        while(left<right){
            int mid = (left + right) / 2;
            if(find_card < sang[mid]){
                right = mid;
            }
            else{
                left = mid +1;
            }
        }
        return right;
    }
}