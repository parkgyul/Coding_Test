import java.util.PriorityQueue;

class Solution {

    private static class Node{
        int pos;
        double endTime; // 도착까지 걸리는 시간

        Node(int pos,double endTime){
            this.pos=pos;
            this.endTime=endTime;
        }
    }

    public int carFleet(int target, int[] position, int[] speed) {
        int n = speed.length;

        // 다른 부분은 손대지 않고 Double.compare를 사용하여 double 비교로 변경
        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)->{
            if(o1.pos == o2.pos) return Double.compare(o1.endTime, o2.endTime);
            else return Integer.compare(o2.pos,o1.pos);
        });

        for(int i=0;i<n;i++){
            int pos = position[i];
            double endTime = findEndTime(speed[i],pos,target);
            pq.offer(new Node(pos,endTime));
        }
        int count = 0;
        int beforePos = pq.peek().pos;
        double beforeEndTime = pq.peek().endTime; // int에서 double로 변경

        while(!pq.isEmpty()){
            Node current = pq.poll();

            //beforePos보다 현재 pos가 위치가 작은데 && 도착하는 시간도 빠르다 => 뒤에 위치한 차가 속도가 더 빠르거나 같다 => 따라 잡힐 일이 없음. 
            if( (beforePos > current.pos && beforeEndTime < current.endTime)) {
            beforePos = current.pos;
            beforeEndTime = current.endTime;
                count++;
            }
   
            
        }
        count++;

        return count ;
    }

    // 다른 부분은 유지하되, 정수 나눗셈/올림을 제거하고 순수 double 나눗셈으로 변경
    private static double findEndTime(int v,int p,int target ){
        int remain = target - p;

        // 소수점이 버려지지 않도록 double로 형변환 후 나눗셈 수행
        double endTime = (double) remain / v; 

        return endTime;
    }
}
