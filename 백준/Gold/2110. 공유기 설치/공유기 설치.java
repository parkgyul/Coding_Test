import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main{
    static int N, C;
    static int[] house;
    static boolean[] visited;
    static int result;
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        house = new int[N];
        visited = new boolean[N];
        for(int i = 0; i <N; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(house);

        int low = 1; // 최소 거리가 가질 수 있는 최솟값
        int high = house[N-1] - house[0] + 1; // 최소 거리가 가질 수 있는 최댓값

        while(low < high){
            int mid = (high + low) / 2;

            if(canInstall(mid) < C){
                high = mid;
            }else {
                low = mid + 1;
            }
        }

        System.out.print(low - 1);
    }

    // 특정 distance에 대해 설치 가능한 공유기 개수 찾기
    public static int canInstall(int distance){
        int count = 1;
        int lastHouse = house[0];

        for(int i = 1; i < N; i++){
            int install = house[i];

            if(install - lastHouse >= distance){
                count++;
                lastHouse = install;
            }
        }

        return count;
    }
}