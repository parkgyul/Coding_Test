import java.io.*;
import java.util.*;

public class Main {
    static boolean DEBUG = true;
    static int N, M;
    static int[][] map;
    static List<Box> boxes;
    static class Box{
        int k, r, c, h, w;
        Box(int k, int r, int c, int h, int w){
            this.k = k;
            this.r = r;
            this.c = c;
            this.h = h;
            this.w = w;
        }
    }
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        boxes = new ArrayList<>();

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            Box box = new Box(k, 1, c, h, w);
            moveDown(box);
            boxes.add(box);
        }

        StringBuilder result = new StringBuilder();

        while(!boxes.isEmpty()){
            Box leftBox = findLeftBox();

            if(leftBox != null){
                result.append(leftBox.k).append("\n");
                boxes.remove(leftBox); // boxes list에서 없애기
                place(leftBox, 0); // map에서 없애기
                gravitiy();
            }

            Box rightBox = findRightBox();

            if(rightBox != null){
                result.append(rightBox.k).append("\n");
                boxes.remove(rightBox); // boxes list에서 없애기
                place(rightBox, 0); // map에서 없애기
                gravitiy();
            }
        }

        System.out.print(result);
    }

    static void gravitiy(){
        while(true){
            boolean isMoved = false;

            for(Box box : boxes){
                place(box, 0);
                if(canPlace(box)){
                    box.r++;
                    isMoved = true;
                }

                place(box, box.k);
            }

            if(!isMoved) break;
        }
    }

    static Box findLeftBox(){
        Box removed = null;
        for(Box box : boxes){
            if(canPullLeft(box)){
                if(removed == null || removed.k > box.k){
                    removed = box;
                }
            }
        }

        return removed;
    }

    static boolean canPullLeft(Box box){
        for(int i = box.r; i < box.r + box.h; i++){
            for(int j = 1; j < box.c ; j++){
                if(map[i][j] != 0) return false;
            }
        }

        return true;
    }

    static Box findRightBox(){
        Box removed = null;
        for(Box box : boxes){
            if(canPullRight(box)){
                if(removed == null || removed.k > box.k){
                    removed = box;
                }
            }
        }

        return removed;
    }

    static boolean canPullRight(Box box){
        for(int i = box.r; i < box.r + box.h; i++){
            for(int j = N; j >= box.c + box.w ; j--){
                if(map[i][j] != 0) return false;
            }
        }

        return true;
    }



    static void place(Box box, int value){
        for(int i = box.r; i < box.r + box.h; i++){
            for(int j = box.c; j < box.c + box.w; j++){
                map[i][j] = value;
            }
        }
    }

    static void moveDown(Box box){
        while(true){
            if(canPlace(box)){
                box.r++;   
            }else{
                break;
            }
        }

        place(box, box.k);
    }

    static boolean canPlace(Box box){
        // 현재의 다음칸을 검증.
        if(box.r + box.h -1 + 1 > N) return false;

        for(int j = box.c; j < box.c+box.w; j++){
            if(map[box.r+box.h -1 + 1][j] != 0) return false;
        }

        return true;
    }

    static void print(String title){
        if(!DEBUG) return;
        StringBuilder sb = new StringBuilder();
        sb.append("==" + title + "==\n");
        for(int i = 1; i<= N; i++){
            for(int j = 1; j <= N; j++){
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}