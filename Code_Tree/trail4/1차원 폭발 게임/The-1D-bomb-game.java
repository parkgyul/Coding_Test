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

        boolean didExplode = true;

        while (didExplode) {
            didExplode = false;
            int currIdx = 0;

            while (currIdx < endOfArray) {
                int endIdx = getEndIdxOfExplosion(currIdx, arr[currIdx]);

                if (endIdx - currIdx + 1 >= M) {
                    cutArray(currIdx, endIdx);
                    didExplode = true;
                } else {
                    currIdx++;
                }
            }
        }

        System.out.println(endOfArray);

        for (int i = 0; i < endOfArray; i++) {
            System.out.println(arr[i]);
        }
    }

    public static int getEndIdxOfExplosion(int startIdx, int currNum) {
        int endIdx = startIdx + 1;

        while (endIdx < endOfArray) {
            if (arr[endIdx] == currNum) {
                endIdx++;
            } else {
                break;
            }
        }
    
        return endIdx - 1;
    }
    
    public static void cutArray(int startIdx, int endIdx) {
        int cutLen = endIdx - startIdx + 1;

        for (int i = endIdx + 1; i < endOfArray; i++) {
            arr[i - cutLen] = arr[i];
        }
        
        endOfArray -= cutLen;
    }
}