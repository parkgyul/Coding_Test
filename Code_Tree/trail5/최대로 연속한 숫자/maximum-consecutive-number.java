import java.io.*; 
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

       TreeSet<Integer> nums = new TreeSet<>();
       TreeSet<Info> set = new TreeSet<>();

       nums.add(-1);
       nums.add(N+1);
       set.add(new Info(N+1, -1, N+1));

       st = new StringTokenizer(br.readLine());
       for(int i = 0; i < M; i++){
            int num = Integer.parseInt(st.nextToken());

            nums.add(num);

            int l = nums.lower(num);
            int h = nums.higher(num);

            set.remove(new Info(h-l-1, l, h));
            set.add(new Info(num-l-1, l, num));
            set.add(new Info(h-num-1, num, h));

            sb.append(set.first().len).append("\n");
       }

       System.out.print(sb);
    }

    static class Info implements Comparable<Info>{
        int len, left, right;

        Info(int len, int left, int right){
            this.len = len;
            this.left = left;
            this.right = right;
        }

        public int compareTo(Info o){
            if(this.len != o.len) return o.len - this.len;
            else if(this.left != o.left) return this.left - o.left;
            else return this.left - o.left;
        }
    }
}