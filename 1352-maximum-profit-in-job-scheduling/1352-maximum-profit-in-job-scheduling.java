class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        // 初始化存储工作信息的数组
        int[][] jobs = new int[n][3];
        for (int i = 0; i< n; i++) {
            jobs[i] = new int[] {startTime[i], endTime[i], profit[i]};
        }
        // 按工作的结束时间排序
        Arrays.sort(jobs, (a, b) -> ( a[1] - b[1]));
        // 初始化 TreeMap，用于存储到某一时间点为止可以获得的最大利润
        TreeMap<Integer, Integer> dp = new TreeMap<>(); // <Time, profit>
        // 在时间点 0，利润是 0
        dp.put(0, 0);
        // 遍历每个工作
        for (int[] job : jobs) {
            // 找到当前工作开始之前可以获得的最大利润，并加上当前工作的利润
            int cur = dp.floorEntry(job[0]).getValue() + job[2];
            // 如果当前工作能够带来更多的利润，则更新 TreeMap
            if (cur > dp.lastEntry().getValue()) {
                dp.put(job[1], cur);
            }
        }
        // 返回最终的最大利润
        return dp.lastEntry().getValue();
    }
}