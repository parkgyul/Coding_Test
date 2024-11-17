import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.HashMap;

class Main
{
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());

        int[] arr = new int[26];

        for(int i = 0; i<num; i++){
            Arrays.fill(arr,0);
            String str = br.readLine().replace(" ","");
            boolean isOverTwo = false;
            for(int j = 0; j<str.length(); j++){
                arr[str.charAt(j) - 'a']++;
            }
            
            int max_index= 0;
            for(int j = 0; j<26; j++){
                if( arr[max_index] < arr[j])
                    max_index = j;
            }

            for(int j = 0; j<26; j++){
                if(arr[max_index] == arr[j] && max_index != j) {
                    isOverTwo = true;
                }
            }
            System.out.println(isOverTwo ? "?" : (char)(max_index + 'a'));
        }
    }
}