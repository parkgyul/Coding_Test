import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

public class Main{
    static int N, K;
    static int[] belt;
    static boolean[] robot;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        belt = new int[2 * N];
        robot = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }

        int step = 0;

        while(true){
            step ++;

            rotate();
            moveRobot();
            putRobot();

            if(check()) break;
        }

        System.out.print(step);
    }

    public static boolean check(){
        int cnt = 0;
        for(int i = 0 ; i < 2*N; i++){
            if(belt[i] == 0) cnt++;
        }

        return cnt >= K;
    }

    private static void rotate(){
        int last = belt[2*N-1];

        for(int i = 2*N-1; i > 0; i--){
            belt[i] = belt[i-1];
        }

        for(int i = N-1; i > 0; i--){
            robot[i] = robot[i-1];
        }

        belt[0] = last;
        robot[N-1] = false;
        robot[0] = false;
    }

    private static void moveRobot(){
        for(int i = N-1; i > 0; i--){
            if(!robot[i] && robot[i-1] && belt[i] >= 1){
                robot[i] = true;
                robot[i-1] = false;
                belt[i] --;
            }
        }

        robot[N-1] = false;
    }

    private static void putRobot(){
        if(belt[0] >= 1){
            robot[0] = true;
            belt[0] --;
        }
    }
}