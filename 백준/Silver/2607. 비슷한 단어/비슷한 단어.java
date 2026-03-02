import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String standard = br.readLine();

        int result = 0;

        for(int i = 1; i < n; i++){
            String word = br.readLine();
            int cnt = 0;
            int[] arr = new int[26];
            for(int j = 0; j < standard.length(); j++){
                arr[standard.charAt(j) - 'A'] ++;
            }

            for(int j =0; j<word.length(); j++){
                int index = word.charAt(j) - 'A';
                if(arr[index] > 0){
                    cnt++; // 겹친다는 의미
                    arr[index] --;
                }
            }

            // 길이가 같음. (구성이 똑같거나 다른게 하나 추가되었거나)
            if(standard.length() == word.length() && (standard.length() == cnt || standard.length() -1 == cnt))
                result ++;
            // 구성 하나 빠짐.
            else if(standard.length()-1 == word.length() && standard.length()-1 == cnt)
                result++;
            // 구성 하나 추가됨.
            else if(standard.length()+1 == word.length() && standard.length() == cnt)
                result++;
        }

        System.out.print(result);
    }
}