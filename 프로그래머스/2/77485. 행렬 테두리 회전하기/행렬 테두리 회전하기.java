class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        
        int[][] arr = new int[rows][columns];
        
        int cnt = 1;
        
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                arr[i][j] = cnt++;
            }
        }
        
        cnt = 0;
        for(int[] query : queries){
            int si = query[0]-1;
            int sj = query[1]-1;
            int ei = query[2]-1;
            int ej = query[3]-1;
        
            
            int temp = arr[si][sj];
            int min = temp;
            
            for(int i = si; i <= ei-1; i++){
                arr[i][sj] = arr[i+1][sj];
                if(min > arr[i][sj]) min = arr[i][sj];
            }
            
            for(int j = sj; j <= ej-1; j++){
                arr[ei][j] = arr[ei][j+1];
                if(min > arr[ei][j]) min = arr[ei][j];
            }
            
            for(int i = ei; i >= si+1; i--){
                arr[i][ej] = arr[i-1][ej];
                if(min > arr[i][ej]) min = arr[i][ej];
            }
            
            for(int j = ej; j >= sj+1; j--){
                arr[si][j] = arr[si][j-1];
                if(min > arr[si][j]) min = arr[si][j];
            }
            
            arr[si][sj+1] = temp;
            answer[cnt++] = min;
        }
        
        return answer;
    }
}