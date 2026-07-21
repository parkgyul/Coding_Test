import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        HashSet<Integer>[] sets = new HashSet[N+1];
        int[] arr = new int[N+1];

        int[] seat = new int[N+1];

        for(int i = 1; i<= N; i++){
            HashSet<Integer> set = new HashSet<>();
            set.add(i);
            sets[i] = set;
            seat[i] = i;
        }

        int[] s = new int[K];
        int[] e = new int[K];

        for(int i = 0 ; i < K; i++){
            st = new StringTokenizer(br.readLine());
            s[i] = Integer.parseInt(st.nextToken());
            e[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < 3; i++){
            for(int idx = 0; idx < K; idx++){
                sets[seat[s[idx]]].add(e[idx]);
                sets[seat[e[idx]]].add(s[idx]);

                int temp = seat[s[idx]];
                seat[s[idx]] = seat[e[idx]];
                seat[e[idx]] = temp;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++){
            sb.append(sets[i].size()).append("\n");
        }

        System.out.print(sb);
    }
}
