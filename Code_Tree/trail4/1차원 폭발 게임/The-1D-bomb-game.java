import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer> list = new ArrayList<>();

        for(int i =0 ; i < N; i++){
            int num = Integer.parseInt(br.readLine());
            list.add(num);
        }

        boolean flag = true;
        while(flag && !list.isEmpty()){
            int temp = list.get(list.size() -1);
            int idx = list.size() -1;
            int cnt = 1;
            flag = false;
            for(int i = list.size() -2; i >= 0; i--){
                if(temp == list.get(i)){
                    cnt++;
                    idx = i;
                }else{
                    if(cnt >= M){
                        while(cnt-- > 0){
                            list.remove(idx);
                        }
                        flag = true;
                    }

                    temp = list.get(i);
                    idx = i;
                    cnt = 1;
                }
            }

            if(cnt >= M){
                while(cnt-- > 0){
                    list.remove(idx);
                 }
            }
        }

        System.out.println(list.size());
        for(int num : list){
            System.out.println(num);
        }

    }
}