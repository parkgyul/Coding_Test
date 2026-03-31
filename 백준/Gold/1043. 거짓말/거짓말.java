import java.io.IOException;
import java.io.BufferedReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main{
    static int[] parents;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 사람 수
        int M = Integer.parseInt(st.nextToken()); // 파티 수


        st = new StringTokenizer(br.readLine());

        parents = new int[N+1];
        for(int i = 1; i < N+1; i++){
            parents[i] = i;
        }


        int truthCount = Integer.parseInt(st.nextToken());
        int[] truth = new int[truthCount];
        for(int i = 0; i < truthCount; i++){
            truth[i] = Integer.parseInt(st.nextToken());
        }

        List<List<Integer>> parties = new ArrayList<>();
        for(int i = 0; i < M; i++){ // 파티 수
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());

            List<Integer> party = new ArrayList<>();
            for(int j = 0; j < cnt; j++){
                party.add(Integer.parseInt(st.nextToken()));
            }

            parties.add(party);

            // 같은 파티는 union
            for(int j = 0 ; j < party.size() - 1; j++){
                union(party.get(j), party.get(j + 1));
            }
        }

        int result = 0;

        for(List<Integer> party : parties){
            boolean canLie = true;

            for(int person : party){
                for(int t : truth){
                    if(find(person) == find(t)){
                        canLie = false;
                        break;
                    }
                }
                if(!canLie) break;
            }

            if(canLie) result++;
        }

        System.out.println(result);
    }
    static int find(int x){
        if(parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    static void union(int a, int b){
        int pa = find(a);
        int pb = find(b);
        if(pa != pb) parents[pb] = pa;
    }
}