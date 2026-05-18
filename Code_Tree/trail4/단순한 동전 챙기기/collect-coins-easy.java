import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static boolean[] nums;
    static Map<Integer, Point> map;
    static int min;
    static int ei, ej;
    static int si, sj;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        nums = new boolean[10];

        map = new HashMap<>();

        int si = -1, sj = -1;
        for(int i = 0; i < N; i++){
            String str = br.readLine();
            for(int j = 0; j < N; j++){
                char ch = str.charAt(j);
                if(ch == 'S'){
                    si = i;
                    sj = j;
                }else if(ch == 'E'){
                    ei = i;
                    ej = j;
                }else if(1<= ch - '0' && ch - '0' <= 9){
                    int num = ch -'0';
                    nums[num] = true;
                    map.put(num, new Point(i, j));
                }
            }
        }
        if(map.size() < 3){
            System.out.print(-1);
            return;
        }

        min = Integer.MAX_VALUE;
        
        dfs(1, 0, 0, si, sj);

        System.out.println(min);
    }

    static void dfs(int index, int coinCnt, int cnt, int prevI, int prevJ){
        if(min <= cnt){
            return;
        }

        if(coinCnt >= 3){
            min = Math.min(min, cnt+ (int)(Math.abs(ei- prevI) + Math.abs(ej - prevJ)));
            return;
        }

        for(int i = index; i <= 9; i++){
            if(!nums[i]) continue;

            Point p = map.get(i);
            dfs(i+1, coinCnt+1, cnt+(int)(Math.abs(p.i - prevI) + Math.abs(p.j - prevJ)), p.i, p.j);
        }
    }

    static class Point{
        int i, j;
        Point(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
}