import java.io.IOException;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        long[][] meetings = new long[N][2];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            meetings[i][0] = Long.parseLong(st.nextToken());
            meetings[i][1] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(meetings, (a, b) -> {
            if(a[1] == b[1]) return Long.compare(a[0], b[0]);

            return Long.compare(a[1], b[1]);
        });

        long endTime = Long.MIN_VALUE;
        int result = 0;
        for(long[] meeting : meetings){
            long start = meeting[0];
            long end = meeting[1];

            if(endTime <= start){
                endTime = end;
                result++;
            }
        }

        System.out.print(result);
    }
}