import java.io.*;
import java.util.*;

public class Main {
    static boolean DEBUG = false;
    static int N, T;
    static int[][] F; // 신봉 음식
    static int[][] B; // 신앙심
    static final int[] food = new int[]{7, 3, 5, 6, 4, 2, 1};
    static StringBuilder result;
    static int[] dr = {-1, 1, 0, 0}; // 위 아래 왼 오른
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        // 신봉 음식 입력 받기
        F = new int[N][N];
        for(int i = 0; i < N; i++){
            String str = br.readLine();
            for(int j = 0; j < N; j++){
                char ch = str.charAt(j);
                int num = -1;
                if(ch == 'T') num = 1; //민트
                else if(ch == 'C') num = 2; // 초코
                else if(ch == 'M') num = 4; // 우유
                F[i][j] = num;
            }
        }

        // 초기 신앙심 입력
        B = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                B[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = new StringBuilder();
        while(T-- > 0){
            // 1. 아침 시간
            increaseB();
            print("아침 시간");

            // 2. 점심 시간
            List<Group> groups = makeGroups();
            // print("점심 시간 1 ");
            divideB(groups);
            print("점심 시간 2 ");

            // 3. 저녁 시간
            // printGroups(groups);
            sortGroups(groups);
            // printGroups(groups);
            spreadB(groups);

            // 4. 저녁 끝
            printResult();
        }

        System.out.print(result);
    }

    static void spreadB(List<Group> groups){
        boolean[][] isProtected = new boolean[N][N];

        for(Group group : groups){
            // 변하지 않음.
            int bestR = group.bestR;
            int bestC = group.bestC;
            int bestF = F[bestR][bestC];
            int bestB = group.bestB;

            // 방어상태이면 전파하지 않음.
            if(isProtected[bestR][bestC]) continue;

            // 변할 수 있음.
            int preR = bestR;
            int preC = bestC;

            // 신앙심 B 중 1만 남긴
            B[preR][preC] = 1;

            int dir = bestB % 4; // 방향

            // System.out.println("전파 : " + bestR + ", " + bestC + " == " + dir);

            // 간절함
            int x = bestB - 1;

            while(x > 0){
                // 다음 위치
                preR += dr[dir];
                preC += dc[dir];
                
                // 격자 밖으로
                if(isOutside(preR, preC)) break;
                // 신봉 음식이 완전히 같음
                if(bestF == F[preR][preC]) continue;

                // 강한 전파
                if(x > B[preR][preC]){
                    // 전파자와 동일한 음식 신봉
                    F[preR][preC] = bestF;
                    
                    x -= (B[preR][preC]+1); // 전파자는 간절함이 (y+1)만큼 깎임.
                    B[preR][preC] += 1; // 전파 대상의 신앙심은 1 증가하게 됩니다.
                    // System.out.println("강한 전파 : " + x);
                }else{ // 약한 전파
                    // 기존에 관심을 가지고 있던 기본 음식들과 전파자가 관심을 가지고 있는 기본 음식을 모두 합친 음식을 신봉
                    // System.out.println("전파 대상 : " +F[preR][preC] + " 전파자 : "+ bestF + " 전파 후 : " + (F[preR][preC] | bestF));
                    F[preR][preC] = (F[preR][preC] | bestF);
                    B[preR][preC] += x;
                    // System.out.println("약한 전파 : " + B[preR][preC]);
                    x = 0; // 전파 더이상 진행 x
                }

                // 전파 당한 학생이면 방어 상태.
                isProtected[preR][preC] = true;
            }
            print("전파 ");
        }
    }

    static void printGroups(List<Group> groups){
        if(!DEBUG) return;

        StringBuilder sb = new StringBuilder();
        sb.append("== print group ==\n");
         for(Group group : groups){
            sb.append(group.priority + " " + group.bestB + " " + group.bestR + " " + group.bestC + " \n");
        }
        System.out.println(sb);
    }

    static void sortGroups(List<Group> groups){
        groups.sort((a, b)->{
            if(a.priority == b.priority){
                if(a.bestB == b.bestB){
                    if(a.bestR == b.bestR){
                        return a.bestC - b.bestC;
                    }
                    return a.bestR - b.bestR;
                }

                return b.bestB - a.bestB;
            }
            return a.priority - b.priority;
        });
    }

    static void divideB(List<Group> groups){
        for(Group group : groups){
            int bestB = group.bestB;
            int bestR = group.bestR;
            int bestC = group.bestC;

            List<int[]> people = group.people;
            
            // 대표자의 신앙심이 (그룹크기-1) 만큼 올라감.
            group.setBestB(bestB + people.size()-1);
            B[bestR][bestC] += (people.size()-1);

            for(int[] person : people){
                // 대표자는 제외.
                if(person[0] == bestR && person[1] == bestC){
                    continue;
                }

                // 대표자 제외 신앙심 -1.
                B[person[0]][person[1]] -= 1;
            }
        
        }
    }

    static List<Group> makeGroups(){
        List<Group> list = new ArrayList<>();
        // Group : 그룹 우선 순위(단일, 이중, 삼중), 대표자 신앙심, 사람들(List<int[]>)

        boolean[][] visited = new boolean[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(!visited[i][j]){
                    Group newGroup = findGroup(i, j, visited);
                    list.add(newGroup);
                }
            }
        }

        return list;
    }

    static Group findGroup(int r, int c, boolean[][] visited){
        // 신봉하는 음식
        int f = F[r][c]; 

        // 같은 그룹 사람들 위치 저장
        List<int[]> locs = new ArrayList<>();
        locs.add(new int[]{r, c});
        visited[r][c] = true;

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r, c});

        // 대표자 찾기 위함.
        int bestB = B[r][c]; // 가장 큰 신앙심
        int bestR = r;
        int bestC = c;

        while(!q.isEmpty()){
            int[] cur = q.poll();

            if(bestB < B[cur[0]][cur[1]]){
                bestB = B[cur[0]][cur[1]];
                bestR = cur[0];
                bestC = cur[1];
            }else if(bestB == B[cur[0]][cur[1]]){
                if(cur[0] < bestR){
                    bestR = cur[0];
                    bestC = cur[1];
                }else if(cur[0] == bestR && cur[1] < bestC ){
                    bestC = cur[1];
                }
            } 

            for(int i = 0; i < 4; i++){
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                if(isOutside(nr, nc)) continue;
                if(visited[nr][nc] || f != F[nr][nc]) continue;
                
                locs.add(new int[]{nr, nc});
                visited[nr][nc] = true;
                q.add(new int[]{nr, nc});
            }
        }

        return new Group(getPriority(f), bestB, bestR, bestC, locs);
    }

    static int getPriority(int f){
        if(f == 4 || f == 2 || f == 1) return 0;
        else if(f == 3 || f == 5 || f == 6) return 1;
        else return 2;
    }

    static boolean isOutside(int r, int c){
        return (r < 0 || r >= N || c < 0 || c >= N);
    }

    static class Group{
        int priority, bestB, bestR, bestC;
        List<int[]> people;

        Group(int priority, int bestB, int bestR, int bestC, List<int[]> people){
            this.priority = priority;
            this.bestB = bestB;
            this.bestR = bestR;
            this.bestC = bestC;
            this.people = people;
        }

        public void setBestB(int bestB){
            this.bestB = bestB;
        }
    }

    static void increaseB(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                B[i][j] += 1;
            }
        }
    }

    static void printResult(){
        int[] sum = new int[8];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                sum[F[i][j]] += B[i][j];
            }
        }

        for(int i = 0; i < 7; i++){
            result.append(sum[food[i]] + " ");
        }
        result.append("\n");
    }

    static void print(String title){
        if(!DEBUG) return;
        StringBuilder sb = new StringBuilder();
        sb.append("== turn : " + T + " " + title + "==\n");
        // sb.append("-- F --\n");
        // for(int i = 0; i < N; i++){
        //     for(int j = 0; j < N; j++){
        //         sb.append(F[i][j] + " ");
        //     }
        //     sb.append("\n");
        // }
        sb.append("-- B --\n");
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                sb.append(B[i][j] + " ");
            }
             sb.append("\n");
        }

        System.out.println(sb);
    }
}