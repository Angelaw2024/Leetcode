// O(nlogn) O(n)
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
            // floorEntry: 返回小于等于key的第一个元素
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

// O(nlogn) O(n)
class Solution1 {
    // 最大工作数为50000
    int[] memo = new int[50001];

    // 通过二分查找找到下一个不与当前工作冲突的工作的索引
    private int findNextJob(int[] startTime, int lastEndingTime) {
        int start = 0, end = startTime.length - 1, nextIndex = startTime.length;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (startTime[mid] >= lastEndingTime) {
                nextIndex = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return nextIndex;
    }

    // 递归函数，找到从当前位置开始的最大利润
    private int findMaxProfit(List<List<Integer>> jobs, int[] startTime, int n, int position) {
        if (position == n) {
            return 0;
        }
        // 如果已经计算过，直接返回结果
        if (memo[position] != -1) {
            return memo[position];
        }
        int nextIndex = findNextJob(startTime, jobs.get(position).get(1));
        int maxProfit = Math.max(findMaxProfit(jobs, startTime, n, position + 1), 
                        jobs.get(position).get(2) + findMaxProfit(jobs, startTime, n, nextIndex));
        // 存储结果以备将来使用（记忆化）
        return memo[position] = maxProfit;
    }

    // 主函数
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        List<List<Integer>> jobs = new ArrayList<>();
        Arrays.fill(memo, -1); // 标记所有值为-1，用于识别是否已经计算过答案

        // 存储工作细节
        int length = profit.length;
        for (int i = 0; i < length; i++) {
            ArrayList<Integer> currJob = new ArrayList<>();
            currJob.add(startTime[i]);
            currJob.add(endTime[i]);
            currJob.add(profit[i]);
            jobs.add(currJob);
        }
        // 对工作按开始时间排序
        jobs.sort(Comparator.comparingInt(a -> a.get(0)));

        // 二分查找将用于startTime，因此将其存储为单独的列表
        for (int i = 0; i < length; i++) {
            startTime[i] = jobs.get(i).get(0);
        }
        // 找到最大利润并返回
        return findMaxProfit(jobs, startTime, length, 0);
    }
}
