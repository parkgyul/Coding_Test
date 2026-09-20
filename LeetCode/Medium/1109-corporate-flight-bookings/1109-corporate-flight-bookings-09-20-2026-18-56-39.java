class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] seats = new int[n];

        for(int[] booking : bookings){
            int start = booking[0];
            int end = booking[1];

            for(int i = start-1; i <= end-1; i++){
                seats[i] += booking[2];
            }
        }

        return seats;
    }
}