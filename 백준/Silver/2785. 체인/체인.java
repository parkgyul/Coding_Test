import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();

        ArrayList<Integer> list=new ArrayList<Integer>();

        for(int i=0; i<n; i++){
            list.add(sc.nextInt());
        }

        if(n<=3){
            System.out.println(1); // 고리의 개수는 1
            return;
        }

        Collections.sort(list); // 오름차순 정렬

        int cnt=0;

        while(list.size()>1){
            list.set(0,list.get(0)-1); // 제일 작은 체인 개수 - 1
            list.remove(list.size()-1); // 큰 체인 부터 하나씩 삭제
            cnt++; // 연결되었으니 카운트 증가

            if(list.get(0)==0) list.remove(0); // 체인의 개수가 0이라면, 해당 인덱스 삭제
        }
        System.out.println(cnt);
    }
}