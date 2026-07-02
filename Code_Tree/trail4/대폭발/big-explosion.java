import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken())-1;
        int c = Integer.parseInt(st.nextToken())-1;

        boolean[][] boom = new boolean[N][N];
        boom[r][c] = true;

        List<int[]> list = new ArrayList<>();
        list.add(new int[]{r, c});
        int cnt = 1;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int time = 1;
    
        while(time <= M){
            List<int[]> newList = new ArrayList<>();
            newList.addAll(list);
            for(int[] l : list){
                int i = l[0];
                int j = l[1];

                for(int dir = 0; dir < 4; dir++){
                    int ni = i + dx[dir] * (int)Math.pow(2, time-1);
                    int nj = j + dy[dir] * (int)Math.pow(2, time-1);

                    if(ni < 0 || nj < 0 || ni >= N || nj >= N) continue;
                    if(boom[ni][nj]) continue;

                    boom[ni][nj] = true;
                    newList.add(new int[]{ni, nj});
                    cnt++;
                }
            }

            time ++;
            list = newList;
        }

        System.out.println(cnt);
    }

}