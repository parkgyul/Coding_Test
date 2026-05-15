class Solution {
    static int[][] map;
    public int solution(int[][] board, int[][] skills) {
        int answer = 0;
        int n = board.length;
        int m = board[0].length;
        
        map = new int[n+1][m+1];
        
        
        for(int[] skill : skills){
            int type = skill[0];
            int r1 = skill[1];
            int c1 = skill[2];
            int r2 = skill[3];
            int c2 = skill[4];
            int degree = skill[5];
            
            degree = type == 1 ? -degree: degree;
            
            map[r1][c1] += degree;
            map[r1][c2+1] -= degree;
            map[r2+1][c1] -= degree;
            map[r2+1][c2+1] += degree;
        }
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                map[i][j+1] += map[i][j];
            }
        }
        
        for(int j = 0; j < m; j++){
            for(int i = 0; i < n; i++){
                map[i+1][j] += map[i][j];
            }
        }
    
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(map[i][j]+board[i][j] > 0) answer++;
            }
        }
        
        return answer;
    }
}