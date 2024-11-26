import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

class Main{


    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashMap<String, Integer> map = new HashMap<>();

        for(int i =0; i<n; i++){
            String str = br.readLine();
            if(str.length() < m)
                continue;
            if(map.containsKey(str))
                map.put(str, map.get(str) + 1);
            else
                map.put(str, 1);
        }

        ArrayList<String> words = new ArrayList<String>(map.keySet());

        Collections.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //자주 나오는 단어
                if(Integer.compare(map.get(o1), map.get(o2)) != 0){
                    return Integer.compare(map.get(o2), map.get(o1));
                }
                //해당 단어의 길이가 길수록 앞에 배치한다.
                if(o1.length() != o2.length()){
                    return o2.length() - o1. length();
                }
                // 등장 횟수와 길이가 같으면 사전 순으로 정렬
                return o1.compareTo(o2);
            }
        });

        StringBuilder sb = new StringBuilder();
        for (String str : words) {
            sb.append(str + "\n");
        }
        System.out.println(sb);
    }
}
