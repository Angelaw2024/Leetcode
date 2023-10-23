class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        int[][] pair = new int[n][2];
        
        // 将难度和利润组成一对并存储
        for (int i = 0; i < n; i++) {
            pair[i] = new int[]{difficulty[i], profit[i]};
        }
        
        // 根据工作的难度排序
        Arrays.sort(pair, (a, b) -> (a[0] - b[0]));
        
        // 记录在当前或之前难度中能获取的最大利润
        int best = 0;
        for (int i = 0; i < n; i++) {
            best = Math.max(best, pair[i][1]);
            pair[i][1] = best;
        }
        
        // 根据工人的能力排序
        Arrays.sort(worker);
        
        int i = n - 1;
        int res = 0;
        
        // 为每个工人找到他能完成的利润最高的工作
        for (int j = worker.length - 1; j  >= 0; j--) {
            while (i >= 0 && worker[j] < pair[i][0]) i--;
            if (i < 0) break;
            res += pair[i][1];
        }
        
        return res;
    }
}