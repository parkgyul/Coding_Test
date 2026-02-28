import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Main{
    static int[] dx = {0, 1, 0, -1}; // 오 아 왼 위
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 보드 크기
        int k = Integer.parseInt(br.readLine()); // 사과 갯수

        boolean[][] arr = new boolean[n][n]; // 사과 위치
        boolean[][] snake = new boolean[n][n];  // 뱀의 위치

        StringTokenizer st ;
        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            arr[Integer.parseInt(st.nextToken()) -1][Integer.parseInt(st.nextToken())-1] = true; // 사과 위치
        }

        Deque<Point> q = new ArrayDeque<>();

        int current_i = 0;
        int current_j = 0;
        snake[current_i][current_j] = true;
        q.addFirst(new Point(current_i, current_j));

        int time = 0;
        int dir = 0;

        st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken()); // 뱀의 방향 변환 횟수

        int[] turnTime = new int[l];
        char[] turnDir = new char[l];
        for(int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            turnTime[i] = Integer.parseInt(st.nextToken());
            turnDir[i] = st.nextToken().charAt(0);

        }

        int idx = 0;
        while(true){
            time ++;

            Point head = q.getFirst();

            int next_i = head.i + dx[dir];
            int next_j = head.j + dy[dir];

            if(next_i <0 || next_j <0 || next_i >= n || next_j >= n){ // 벽에 부딪힘
                System.out.print(time);
                return;
            }

            if(snake[next_i][next_j]) { // 자기 자신에 부딪힘.
                System.out.print(time);
                return;
            }

            if(arr[next_i][next_j]){ // 사과 있을때
                q.addFirst(new Point(next_i, next_j)); // 머리 이동
                snake[next_i][next_j] = true;
                arr[next_i][next_j] = false; // 사과 없애기
            }else{ // 사과 없을때
                q.addFirst(new Point(next_i, next_j));  // 머리 이동
                snake[next_i][next_j] = true;

                Point tail = q.removeLast(); // 꼬리 삭제
                snake[tail.i][tail.j] = false;
            }

            if(idx < l && turnTime[idx] == time) { // 방향 전환 타이밍
                if(turnDir[idx] == 'D') dir = (dir+1) % 4; // 오른쪽으로
                else dir = (dir+3) % 4; // 왼쪽으로
                idx ++;
            }

        }
    }

    public static class Point{
        int i, j;
        public Point(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
}