class Solution {
    public long maxRunTime(int n, int[] batteries) {
        
        Arrays.sort(batteries);

        long sum = 0;

        for(int b : batteries){
            sum += b;
        }

        int i = batteries.length - 1;
        while(batteries[i] > sum / n){
            sum -= batteries[i];
            n--;
            i--;
        }

        return sum / n;
    }
}