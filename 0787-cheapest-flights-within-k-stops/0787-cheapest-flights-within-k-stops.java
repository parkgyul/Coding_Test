class Solution {
    static List<int[]>[] nodes;
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        nodes = new ArrayList[n];

        for(int i = 0; i < n; i++){
            nodes[i] = new ArrayList<>();
        }

        for(int[] flight : flights){
            nodes[flight[0]].add(new int[]{flight[1], flight[2]});
        }

        return bfs(n, src, dst, k);
    }

    static int bfs(int n, int src, int dst, int k){
        int[][] costs = new int[n][k+1];
        for(int i = 0; i < n; i++){
            Arrays.fill(costs[i], (int)1e9);
        }

        Queue<int[]> q = new LinkedList<>();
        // 시작점, 돈, k

        q.add(new int[]{src, 0, -1});

        while(!q.isEmpty()){
            int[] cur = q.poll();

            if(cur[2] >= k) continue;


            for(int[] next : nodes[cur[0]]){
                int nextCost = cur[1] + next[1];

                if(costs[next[0]][cur[2]+1] <= nextCost) continue;
                
                costs[next[0]][cur[2]+1] = nextCost;
                q.add(new int[]{next[0], nextCost, cur[2]+1});
            }
        }

        int min = (int)1e9;
        for(int i = 0; i < k+1; i++){
            if(min > costs[dst][i]) min = costs[dst][i];
        }

        return (min == (int)1e9 ? -1 : min);
    }
}