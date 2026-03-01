import org.w3c.dom.Node;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int cnt_recommend = Integer.parseInt(br.readLine());

        HashMap<Integer, Integer> board = new HashMap<>(); // 학생 번호, 게시된 시간

        HashMap<Integer, Integer> students = new HashMap<>(); // 학생 번호, 추천 횟수

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < cnt_recommend; i++) {
            int student = Integer.parseInt(st.nextToken());
            if(students.containsKey(student)){
                students.put(student, students.get(student)+1); // 추천 수 올리기
            }else{
                students.put(student, 1);
            }


            if (!board.containsKey(student)) {
                if (board.size() < n) { // 자리가 있으면 그냥 올리기
                    board.put(student, i);
                    continue;
                }

                int studentToRemove = board.keySet().iterator().next();;

                for (int temp : board.keySet()) {

                    if (students.get(temp) < students.get(studentToRemove)) { // 추천 수가 높으면
                        studentToRemove = temp;
                        continue;
                    }

                    if (students.get(temp).equals(students.get(studentToRemove)) && board.get(temp) < board.get(studentToRemove)) {
                        studentToRemove = temp;
                    }
                }
                board.remove(studentToRemove);  // 게시판에서 삭제
                students.put(studentToRemove, 0); // 추천 횟수 0으로 초기화
                board.put(student, i);
            }

        }

        int[] arr = new int[board.size()];
        int i = 0;
        for(int temp : board.keySet()){
            arr[i++] = temp;
        }

        Arrays.sort(arr);
        for(int result : arr){
            System.out.print(result + " ");
        }
    }
}