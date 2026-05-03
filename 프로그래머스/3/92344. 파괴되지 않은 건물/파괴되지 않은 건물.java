class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        
        int m = board[0].length; // 가로
        int n = board.length; // 세로
        
        int[][] diff = new int[n+1][m+1];
        
        for(int i = 0; i < skill.length; i++){
            int type = skill[i][0];
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = skill[i][5];
            
            
            int value = (type == 1) ? -degree : degree;
            
            diff[r1][c1] += value;
            diff[r1][c2+1] -= value;
            diff[r2+1][c1] -= value;
            diff[r2+1][c2+1] += value;
        }
        
        for(int i = 0; i < n; i++){
            for(int j = 1; j <m; j++){
                diff[i][j] += diff[i][j-1];
            }
        }
        
        for(int j = 0; j < m; j++){
            for(int i = 1; i <n; i++){
                diff[i][j] += diff[i-1][j];
            }
        }
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(board[i][j] + diff[i][j] > 0) answer++;
            }
        }
        
        
        return answer;
    }
}