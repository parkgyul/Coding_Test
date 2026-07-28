import java.io.*; 
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
    
        TreeSet<Question> set = new TreeSet<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            set.add(new Question(P, L));
        }

        int M = Integer.parseInt(br.readLine());

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();

            if(str.equals("ad")){
                int P = Integer.parseInt(st.nextToken());
                int L = Integer.parseInt(st.nextToken());
                set.add(new Question(P, L));
            }else if(str.equals("sv")){
                int P = Integer.parseInt(st.nextToken());
                int L = Integer.parseInt(st.nextToken());
                set.remove(new Question(P, L));
            }else{ // rc
                int num = Integer.parseInt(st.nextToken());
                Question q;
                if(num == 1){
                    q = set.last();
                }else{
                    q = set.first();
                }

                sb.append(q.num).append("\n");
            }
        }

        System.out.print(sb);
    
    }

    static class Question implements Comparable<Question>{
        int num, level;

        Question(int num, int level){
            this.num = num;
            this.level = level;
        }

        public int compareTo(Question o){
            if(this.level == o.level) return this.num - o.num;
            return this.level - o.level;
        }

    }
}