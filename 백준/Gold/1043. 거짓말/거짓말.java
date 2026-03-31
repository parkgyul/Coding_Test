import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;

public class Main{
    static int[] parents;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parents = new int[N+1];
        for(int i = 1; i < N+1; i++){
            parents[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int truthCount = Integer.parseInt(st.nextToken());
        int[] truth = new int[truthCount];

        for(int i =0; i < truthCount; i++){
            truth[i] = Integer.parseInt(st.nextToken());
        }

        List<List<Integer>> parties = new ArrayList<>();
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            List<Integer> party = new ArrayList<>();
            for(int j = 0; j < num; j++){
                int person = Integer.parseInt(st.nextToken());
                party.add(person);
            }

            for(int j = 0; j < party.size() -1; j++){
                union(party.get(j), party.get(j+1));
            }
            parties.add(party);
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

            if(canLie) result ++;
        }

        System.out.print(result);
    }

    static int find(int a){
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    static void union(int a, int b){
        int pa = find(a);
        int pb = find(b);

        if(pa != pb) parents[pb] = pa;
    }
}