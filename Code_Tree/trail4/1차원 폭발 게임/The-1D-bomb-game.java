import java.io.*;
import java.util.*;

public class Main {

    static int N, M, endOfArray;
    static int[] arr;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        for(int i =0 ; i < N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        endOfArray = N;

        boolean didExplode =true;
        while(didExplode){
            didExplode = false;
            int curIdx = 0;

            while(curIdx < endOfArray){
                int endIdx = findEndIdx(curIdx, arr[curIdx]);

                if(endIdx - curIdx + 1 >= M){
                    cutArray(curIdx, endIdx);
                    didExplode = true;
                }else{
                    curIdx ++;
                }
            }
        }

        System.out.println(endOfArray);
        for(int i =0 ; i < endOfArray; i++){
            System.out.println(arr[i]);
        }
    }

    static int findEndIdx(int curIdx, int curNum){
        int endIdx = curIdx +1;
        while(endIdx < endOfArray){
            if(arr[endIdx] == curNum){
                endIdx++;
            }else{
                break;
            }
        }

        return endIdx-1;
    }

    static void cutArray(int curIdx, int endIdx){
        int curLen = endIdx - curIdx + 1;

        for(int i = endIdx+1; i < endOfArray; i++){
            arr[i-curLen] = arr[i];
        }
        endOfArray -= curLen;
    }
}