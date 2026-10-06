import java.io.*;
import java.util.*;

public class Main {

    static int N, M;

    // 0 = 빈칸
    // 나머지 = 택배 번호
    static int[][] board;

    static List<Box> boxes = new ArrayList<>();
    static List<Integer> answer = new ArrayList<>();

    static class Box {

        int k;

        // 왼쪽 위 좌표
        int r, c;

        int h, w;

        Box(int k, int r, int c, int h, int w){
            this.k = k;
            this.r = r;
            this.c = c;
            this.h = h;
            this.w = w;
        }
    }

    /*
     * 해당 택배가 차지하고 있는 영역을
     * value 값으로 설정
     */
    static void draw(Box box, int value){

        for(int r = box.r; r < box.r + box.h; r++){
            for(int c = box.c; c < box.c + box.w; c++){
                board[r][c] = value;
            }
        }
    }

    /*
     * 현재 위치에서 한 칸 아래로 이동 가능한가?
     *
     * 박스의 바로 아래 한 줄만 보면 됨.
     */
    static boolean canMoveDown(Box box){

        int nextRow = box.r + box.h;

        // 바닥
        if(nextRow >= N){
            return false;
        }

        for(int c = box.c; c < box.c + box.w; c++){

            if(board[nextRow][c] != 0){
                return false;
            }
        }

        return true;
    }

    /*
     * 처음 투입된 택배를
     * 바닥까지 떨어뜨림
     */
    static void dropNewBox(Box box){

        while(true){

            if(!canMoveDown(box)){
                break;
            }

            box.r++;
        }

        draw(box, box.k);
    }

    /*
     * 왼쪽으로 그대로 밀었을 때
     * 공간 밖으로 빠져나갈 수 있는지
     */
    static boolean canRemoveLeft(Box box){

        for(int r = box.r; r < box.r + box.h; r++){

            for(int c = 0; c < box.c; c++){

                if(board[r][c] != 0){
                    return false;
                }
            }
        }

        return true;
    }

    /*
     * 오른쪽으로 그대로 밀었을 때
     * 공간 밖으로 빠져나갈 수 있는지
     */
    static boolean canRemoveRight(Box box){

        for(int r = box.r; r < box.r + box.h; r++){

            for(int c = box.c + box.w; c < N; c++){

                if(board[r][c] != 0){
                    return false;
                }
            }
        }

        return true;
    }

    /*
     * 왼쪽으로 제거 가능한 택배 중
     * 번호가 가장 작은 것
     */
    static Box findLeft(){

        Box target = null;

        for(Box box : boxes){

            if(!canRemoveLeft(box)){
                continue;
            }

            if(target == null || box.k < target.k){
                target = box;
            }
        }

        return target;
    }

    /*
     * 오른쪽으로 제거 가능한 택배 중
     * 번호가 가장 작은 것
     */
    static Box findRight(){

        Box target = null;

        for(Box box : boxes){

            if(!canRemoveRight(box)){
                continue;
            }

            if(target == null || box.k < target.k){
                target = box;
            }
        }

        return target;
    }

    /*
     * 제거 후 모든 택배에 중력 적용
     *
     * 더 이상 움직이는 택배가 없을 때까지 반복
     */
    static void gravity(){

        while(true){

            boolean moved = false;

            for(Box box : boxes){

                /*
                 * 자기 자신과 충돌하지 않도록
                 * 잠시 board에서 제거
                 */
                draw(box, 0);

                if(canMoveDown(box)){
                    box.r++;
                    moved = true;
                }

                draw(box, box.k);
            }

            if(!moved){
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br =
                new BufferedReader(
                        new InputStreamReader(System.in)
                );

        StringTokenizer st =
                new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][N];

        /*
         * 택배 투입
         */
        for(int i = 0; i < M; i++){

            st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            // 1-based → 0-based
            int c = Integer.parseInt(st.nextToken()) - 1;

            /*
             * 처음에는 위쪽에서 시작
             */
            Box box = new Box(
                    k,
                    0,
                    c,
                    h,
                    w
            );

            /*
             * 중력에 의해 떨어뜨림
             */
            dropNewBox(box);

            boxes.add(box);
        }

        /*
         * 모든 택배 하차
         */
        while(!boxes.isEmpty()){

            /*
             * 1. 왼쪽
             */
            Box left = findLeft();

            if(left != null){

                answer.add(left.k);

                draw(left, 0);

                boxes.remove(left);

                gravity();
            }

            /*
             * 2. 오른쪽
             *
             * 왼쪽 제거 + 중력 이후
             * 다시 현재 상태에서 찾아야 함.
             */
            Box right = findRight();

            if(right != null){

                answer.add(right.k);

                draw(right, 0);

                boxes.remove(right);

                gravity();
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int k : answer){
            sb.append(k).append('\n');
        }

        System.out.print(sb);
    }
}
