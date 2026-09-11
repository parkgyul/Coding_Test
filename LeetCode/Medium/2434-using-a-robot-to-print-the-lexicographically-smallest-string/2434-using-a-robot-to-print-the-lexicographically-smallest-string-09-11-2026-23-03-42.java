class Solution {
    public String robotWithString(String s) {
        int[] count = new int[26];

        // 앞으로 남아 있는 문자 개수
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        StringBuilder answer = new StringBuilder();
        StringBuilder stack = new StringBuilder();

        int minChar = 0; // 남아 있는 문자 중 최솟값의 인덱스

        for (char c : s.toCharArray()) {
            // s -> t : 스택에 넣기
            stack.append(c);
            count[c - 'a']--;

            // 현재 s에 남은 문자 중 최솟값 찾기
            while (minChar < 26 && count[minChar] == 0) {
                minChar++;
            }

            // t -> 종이 : 꺼내기
            while (stack.length() > 0) {
                char top = stack.charAt(stack.length() - 1);

                // s가 비었거나,
                // top이 앞으로 남은 최소 문자보다 작거나 같으면 출력 가능
                if (minChar == 26 || top - 'a' <= minChar) {
                    answer.append(top);
                    stack.deleteCharAt(stack.length() - 1);
                } else {
                    break;
                }
            }
        }

        return answer.toString();
    }
}