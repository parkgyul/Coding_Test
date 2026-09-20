class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        List<int[]> list = new ArrayList<>();

        for(int i = 0; i < position.length; i++){
            list.add(new int[]{position[i], speed[i]});
        }

        list.sort((a, b) -> {
            // 스피드가 빠른 것 부터
            if(a[0] == b[0]) return b[1] - a[1];
            return b[0] - a[0];
        });

        int sum = 0;

        double time = 0;
        for(int[] car : list){
            double last = (double)(target - car[0])/ (double)car[1];

            // 어차피 못 따라 잡음 새로운 대기열
            if(time < last){
                sum++;
                time = last; 
            }
        }

        return sum;
    }
}