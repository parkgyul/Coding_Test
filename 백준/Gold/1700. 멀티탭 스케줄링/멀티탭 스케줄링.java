import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[k];

        st = new StringTokenizer(br.readLine());
        for(int i =0; i < k; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Set<Integer> plugged = new HashSet<>();
        int cnt = 0;

        for(int i = 0; i< k; i++){
            int current = arr[i];

            // 꽂혀있으면 넘어가기
            if(plugged.contains(current)) continue;

            // 자리가 있으면 그냥 꽂기
            if(plugged.size() < n){
                plugged.add(current);
                continue;
            }

            int deviceToRemove = -1;
            int farthestIndex = -1;

            // 가장 나중에 쓰이는 거 제거
            for(int device : plugged){
                int nextIndex = Integer.MAX_VALUE;

                for(int j = i+1; j < k; j++){
                    if(device == arr[j]){
                        nextIndex = j;
                        break;
                    }
                }

                if(nextIndex > farthestIndex){
                    deviceToRemove = device;
                    farthestIndex = nextIndex;
                }
            }

            plugged.remove(deviceToRemove);
            plugged.add(current);
            cnt++;
        }
        System.out.print(cnt);
    }
}