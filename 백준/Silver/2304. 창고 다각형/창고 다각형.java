import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.util.StringTokenizer;

import java.util.PriorityQueue;

class Main{
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[1001];
        int left = Integer.MAX_VALUE; // 가장 왼쪽
        int right = Integer.MIN_VALUE; // 가장 오른쪽
        int max_index = -1; // 가장 높은 기둥
        int max = Integer.MIN_VALUE;
        int total = 0;
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());

            arr[num] = height; // 기둥 높이 기록

            // 끝 구하기
            left = Math.min(left, num);
            right = Math.max(right, num);

            if(max < height){
                max = height;
                max_index = num;
            }
        }

        total += max;
        int start = arr[left];
        for(int i = left; i < max_index; i++){
            if(start < arr[i]){
                start = arr[i];
            }
            total += start;
        }

        start = arr[right];
        for(int i = right; i > max_index; i--){
            if(start < arr[i]){
                start = arr[i];
            }
            total += start;
        }

        System.out.print(total);
    }
}