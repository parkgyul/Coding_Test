import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer>[][] arr = new ArrayList[N][N];  // <> 없이

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                arr[i][j] = new ArrayList<>();

        
        Map<Integer, int[]> map = new HashMap<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                int num = Integer.parseInt(st.nextToken());
                arr[i][j].add(num);
                map.put(num, new int[]{i, j});
            }
        }
        
        st = new StringTokenizer(br.readLine());

        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

       while (M-- > 0) {
            int num = Integer.parseInt(st.nextToken());
            int[] loc = map.get(num);
            int max = -1, maxI = -1, maxJ = -1;

            for (int dir = 0; dir < 8; dir++) {
                int ni = loc[0] + dx[dir];
                int nj = loc[1] + dy[dir];
                if (ni < 0 || ni >= N || nj < 0 || nj >= N) continue;

                List<Integer> list = arr[ni][nj];
                for (int l : list) {
                    if (max < l) {
                        max = l;
                        maxI = ni;
                        maxJ = nj;
                    }
                }
            }

            if (maxI == -1) {
                continue;
            }

            List<Integer> movedList = arr[loc[0]][loc[1]];
            int idx = movedList.indexOf(num);

            List<Integer> moving = movedList.subList(idx, movedList.size());

            for (int id : moving) {
                map.put(id, new int[]{maxI, maxJ});
            }
            arr[maxI][maxJ].addAll(moving);
            moving.clear();
        }    

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++){
            for(int j =0 ; j < N; j++){
                List<Integer> list = arr[i][j];

                if(list.size() == 0){
                    sb.append("None\n");
                }else{
                    for(int k = list.size()-1; k >= 0; k--){
                        sb.append(list.get(k)).append(" ");
                    }

                    sb.append("\n");
                }
            }
        }                  
        System.out.println(sb);
    }
}