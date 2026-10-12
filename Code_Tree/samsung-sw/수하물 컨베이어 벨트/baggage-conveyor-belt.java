import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_TURN = 200;
    // 1:우 2:상 3:좌 4:하
    static final int[] DR = {0, 1, 0, -1, 0};
    static final int[] DC = {0, 0, -1, 0, 1};

    static int N, B, nowTurn;
    static int[][] map, boxMap;
    static int[] BTurn, BR, BC, BW, notMovedCnt;
    static int[] isFinished;      // 0: 진행 중(투입 전 포함), 그 외: 끝난 턴(항상 양수)
    static boolean[] isBroken;    // 파손 여부 → 출력할 때 -1 곱함
    static boolean[] isPut;
    static List<Integer> boxes;   // 투입 턴 낮은 순 정렬

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine().trim());
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        B = Integer.parseInt(br.readLine().trim());
        BTurn = new int[B + 1]; BR = new int[B + 1]; BC = new int[B + 1]; BW = new int[B + 1];
        notMovedCnt = new int[B + 1];
        isFinished = new int[B + 1];
        isBroken = new boolean[B + 1];
        isPut = new boolean[B + 1];
        boxes = new ArrayList<>();
        for (int id = 1; id <= B; id++) {
            st = new StringTokenizer(br.readLine());
            BTurn[id] = Integer.parseInt(st.nextToken());
            BR[id] = Integer.parseInt(st.nextToken());
            BC[id] = Integer.parseInt(st.nextToken());
            BW[id] = Integer.parseInt(st.nextToken());
            boxes.add(id);
        }
        boxes.sort((a, b) -> BTurn[a] != BTurn[b] ? BTurn[a] - BTurn[b] : a - b);

        boxMap = new int[N + 1][N + 1];
        int unfinished = B;   // 0으로 두면 한 턴도 안 돎
        for (nowTurn = 1; nowTurn <= MAX_TURN && unfinished != 0; nowTurn++) {
            moveBoxes();
            getBoxes();
            broken();
            put();
            unfinished = getUnfinished();
        }

        for (int id = 1; id <= B; id++) {
            sb.append(' ').append((isBroken[id] ? -1 : 1) * isFinished[id]);
        }
        System.out.println(sb.toString().trim());
    }

    // 1. 컨베이어 벨트 이동
    static void moveBoxes() {
        // 벨트 위 상자 모으기
        List<Integer> idx = new ArrayList<>();
        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= N; j++)
                if (boxMap[i][j] != 0) idx.add(boxMap[i][j]);

        // 우선순위: 무거운 순 → id 작은 순
        idx.sort((a, b) -> BW[a] != BW[b] ? BW[b] - BW[a] : a - b);

        int[][] newBoxMap = new int[N + 1][N + 1];
        for (int box : idx) {
            int d = map[BR[box]][BC[box]];
            int nr = BR[box] + DR[d];
            int nc = BC[box] + DC[d];

            // 벽/범위 밖이거나, 이미 누가 차지한 칸이면 이동 실패
            if (nr < 1 || nr > N || nc < 1 || nc > N || map[nr][nc] == 0 || newBoxMap[nr][nc] != 0) {
                rollBack(box, newBoxMap);
            } else {
                newBoxMap[nr][nc] = box;   // 일단 옮겨 둔다 (앞 상자도 움직인다고 가정)
            }
        }

        boxMap = newBoxMap;

        // 좌표 비교로 이동 여부 판정
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (boxMap[i][j] == 0) continue;
                int box = boxMap[i][j];
                if (BR[box] == i && BC[box] == j) {
                    notMovedCnt[box]++;
                } else {
                    BR[box] = i;
                    BC[box] = j;
                    notMovedCnt[box] = 0;
                }
            }
        }
    }

    // 이동 실패한 상자를 원래 칸에 두고, 그 칸을 먼저 차지했던 상자도 연쇄로 되돌린다.
    // BR/BC는 아직 갱신 전이라 원래 위치. 읽는 건 newBoxMap.
    static void rollBack(int box, int[][] newBoxMap) {
        int temp = box;
        int prev = box;
        while (temp != 0) {
            prev = temp;
            int r = BR[prev];
            int c = BC[prev];
            temp = newBoxMap[r][c];      // 내 원래 칸을 먼저 차지한 상자
            newBoxMap[r][c] = prev;      // 나는 제자리로
        }
    }

    // 2-1. 적재
    static void getBoxes() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (boxMap[i][j] > 0 && map[i][j] == 9) {
                    isFinished[boxMap[i][j]] = nowTurn;
                    boxMap[i][j] = 0;
                }
            }
        }
    }

    // 2-2. 파손
    static void broken() {
        for (int i = 1; i <= B; i++) {
            if (isFinished[i] != 0) continue;     // 이미 끝난 상자는 건너뜀
            if (notMovedCnt[i] >= 3) {            // 투입 전 상자는 0이라 자동 제외
                isBroken[i] = true;
            }
        }
    }

    // 3. 투입
    static void put() {
        for (int id : boxes) {
            if (isPut[id]) continue;
            if (BTurn[id] > nowTurn) break;
            if (boxMap[BR[id]][BC[id]] != 0) continue;   // 막혀 있으면 다음 턴
            boxMap[BR[id]][BC[id]] = id;
            isPut[id] = true;
            notMovedCnt[id] = 0;
        }
    }

    static int getUnfinished() {
        int cnt = 0;
        for (int i = 1; i <= B; i++)
            if (isFinished[i] == 0) cnt++;
        return cnt;
    }
}