class MyCalendarTwo {
    List<int[]> bookings;
    List<int[]> agains;

    public MyCalendarTwo() {
        bookings = new ArrayList<>();
        agains = new ArrayList<>();
    }
    
    public boolean book(int startTime, int endTime) {

        // 이미 추가 중복된 거는 넣을 필요 없음
        for(int[] again : agains){
            if(isAgain(startTime, endTime, again[0], again[1])){
                return false;
            }
        }

        for(int[] booking : bookings){
            if(isAgain(startTime, endTime, booking[0], booking[1])){
                int againStart = Math.max(startTime, booking[0]);
                int againEnd = Math.min(endTime, booking[1]);

                agains.add(new int[]{
                    againStart, againEnd
                });
            }
        }

        bookings.add(new int[]{startTime, endTime});

        return true;
    }

    // 겹치는지
    static boolean isAgain(int start1, int end1, int start2, int end2){
        return Math.max(start1, start2) < Math.min(end1, end2);
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(startTime,endTime);
 */