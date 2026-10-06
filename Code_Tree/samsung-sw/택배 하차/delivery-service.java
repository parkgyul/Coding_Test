import java.io.*;
import java.util.*;

public class Main {
    static boolean DEBUG = false;
    static int N, M;
    static List<Box> boxes;
    static int[][] map;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];

        boxes = new ArrayList<>();

        StringBuilder result = new StringBuilder();

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            Box box = new Box(k, 1, c, h, w);
            dropNewBox(box);
            boxes.add(box);

            print("drop");
        }


        while(!boxes.isEmpty()){
            Box left = findLeftBox();

            if(left != null){
                result.append(left.k).append("\n");
                place(left, 0);
                boxes.remove(left);
                gravity();
            }

            Box right = findRightBox();

            if(right != null){
                result.append(right.k).append("\n");
                place(right, 0);
                boxes.remove(right);
                gravity();
            }
        }
        System.out.print(result);
    }

    static void gravity(){
        while(true){
            boolean isMoved = false;

            for(Box box : boxes){
                place(box, 0);

                if(canMoveDown(box)){
                    box.r++;
                    isMoved = true;
                }

                place(box, box.k);
            }

            if(!isMoved){
                break;
            }
        }
    }

    static Box findRightBox(){
        Box target = null;

        for(Box box : boxes){
            if(!canMoveRight(box)){
                continue;
            }

            if(target == null || box.k < target.k){
                target = box;
            }
        }

        return target;
    }

    static boolean canMoveRight(Box box){
        for(int i = box.r; i < box.r+ box.h; i++){
            for(int j = N; j >= box.c + box.w; j--){
                if(map[i][j] != 0) return false;
            }
        }
        return true;
    }

    static Box findLeftBox(){
        Box target = null;

        for(Box box : boxes){
            if(!canMoveLeft(box)){
                continue;
            }

            if(target == null || box.k < target.k){
                target = box;
            }
        }

        return target;
    }

    static boolean canMoveLeft(Box box){
        for(int i = box.r; i < box.r+ box.h; i++){
            for(int j = 1; j < box.c; j++){
                if(map[i][j] != 0) return false;
            }
        }
        return true;
    }

    static void dropNewBox(Box box){
        while(true){
            if(!canMoveDown(box)){
                break;
            }

            box.r++;
        }

        place(box, box.k);
    }

    static boolean canMoveDown(Box box){
        int r = box.r + box.h;

        if(r > N) return false;

        for(int j = box.c; j < box.c + box.w; j++){
            if(map[r][j] != 0) return false;
        }
        return true;
    }

    static void place(Box box, int k){
        for(int i = box.r; i < box.r+ box.h; i++){
            for(int j = box.c; j < box.c+ box.w; j++){
                map[i][j] = k;
            }
        }
    }

     static void print(String title){
        if(!DEBUG) return;
        StringBuilder sb = new StringBuilder();

        sb.append("==" + title + "==\n");
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static class Box{
        int k;
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
}