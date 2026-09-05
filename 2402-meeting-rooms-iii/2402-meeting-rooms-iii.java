class Solution {
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> {
            if(a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });
        
        PriorityQueue<long[]> using = new PriorityQueue<>((a, b) -> {
            if(a[1] == b[1]) return Long.compare(a[0], b[0]);
            return Long.compare(a[1], b[1]);
        });
        PriorityQueue<Integer> empty = new PriorityQueue<>();
        for(int i = 0; i < n; i++){
            empty.add(i);
        }

        int[] room = new int[n];
        for(int[] meeting : meetings){
            while(!using.isEmpty() && using.peek()[1] <= meeting[0]){
                empty.add((int)using.poll()[0]);
            }

            if(!empty.isEmpty()){
                int roomNum = empty.poll();
                using.add(new long[]{roomNum, meeting[1]});
                room[roomNum] ++;
            }else{
                long[] firstEnd = using.poll();
                using.add(new long[]{firstEnd[0], (long)firstEnd[1] + (long)(meeting[1]-meeting[0])});
                room[(int)firstEnd[0]] ++;
            }
        }

        int max = 0;
        int maxIndex = 0;
        for(int i = 0; i < n; i++){
            System.out.print(room[i] + " ");
            if(max < room[i]){
                max = room[i];
                maxIndex = i;
            }
        }

        return maxIndex;
    }
}