import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    static int[][] wheels = new int[5][10];
    static int cnt;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i = 1; i<5; i++){
            String newLine = br.readLine();
            for(int j = 1; j<= 8; j++){
                wheels[i][j] = Integer.parseInt(String.valueOf(newLine.charAt(j-1)));
            }
        }

        cnt = Integer.parseInt(br.readLine());

        for(int i = 0; i<cnt; i++){
            st = new StringTokenizer(br.readLine());
            int wheel = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            shouldLeftRotate(wheel, direction);
            shouldRightRotate(wheel, direction);
            rotate(wheel, direction);
        }
        printResult();
    }

    public static void rotate(int wheel, int direction){
        if(direction == 1){
            for(int i = 9; i>= 2; i--){
                wheels[wheel][i] = wheels[wheel][i-1];
            }
            wheels[wheel][1] = wheels[wheel][9];
        }
        else{
            for(int i = 0; i<=7; i++){
                wheels[wheel][i] = wheels[wheel][i+1];
            }
            wheels[wheel][8] = wheels[wheel][0];
        }
    }

    public static void shouldLeftRotate(int wheel, int direction){
        if(wheel>=2 && wheels[wheel][7] != wheels[wheel-1][3]){
            shouldLeftRotate(wheel-1, whichDirection(direction));
            rotate(wheel-1, whichDirection(direction));
        }
    }

    public static void shouldRightRotate(int wheel, int direction){
        if(wheel<=3 && wheels[wheel][3] != wheels[wheel+1][7]){
            shouldRightRotate(wheel+1, whichDirection(direction));
            rotate(wheel+1, whichDirection(direction));
        }
    }

    public static int whichDirection(int direction){
        return direction == 1 ? -1 : 1;
    }

    public static void printResult(){
        int sum = 0;

        for(int i = 1; i<=4; i++){
            if(wheels[i][1] == 1){
                switch(i){
                    case(1):
                        sum += 1;
                        break;
                    case(2):
                        sum += 2;
                        break;
                    case(3):
                        sum += 4;
                        break;
                    case(4):
                        sum += 8;
                        break;
                }
            }
        }
        System.out.println(sum);
    }
}