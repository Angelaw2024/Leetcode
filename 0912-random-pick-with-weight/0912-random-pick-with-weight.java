class Solution {
/* 设数组 w 的权重之和为 total。根据题目的要求，我们可以看成将 [1,total] 范围内的所有整数分成 n 个部分（其中 n 是数组 w 的长度），第 i 个部分恰好包含 w[i] 个整数，并且这 n 个部分两两的交集为空。随后我们在 [1,total] 范围内随机选择一个整数 x，如果整数 x 被包含在第 i 个部分内，我们就返回 i。
时间复杂度：初始化O(n), 每次选O(log n)
空间复杂度：O(n)
 */
    int[] wSums;
    Random r;
    public Solution(int[] w) {
        this.r = new Random();
        for(int i=1; i<w.length; ++i)
            w[i] += w[i-1];
        this.wSums = w;
    }
    
    public int pickIndex() {
        int n = wSums.length - 1;
        int idx = r.nextInt(wSums[n]) + 1;
        int l = 0, r = n;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (wSums[mid] < idx) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}



/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */