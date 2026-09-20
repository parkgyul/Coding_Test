class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {

        int[] seats = new int[n + 1];

        for (int[] booking : bookings) {
            int start = booking[0] - 1;
            int end = booking[1];

            seats[start] += booking[2];
            seats[end] -= booking[2];
        }

        for (int i = 1; i < n; i++) {
            seats[i] += seats[i - 1];
        }

        return Arrays.copyOf(seats, n);
    }
}